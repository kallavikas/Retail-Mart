#!/usr/bin/env groovy
pipeline {

    /*
     * Run everything on an existing agent configured with a label 'docker'.
     * This agent will need docker, git and a jdk installed at a minimum.
     */
    agent {
        node {
            label 'docker'
        }
    }

    // using the Timestamper plugin we can add timestamps to the console log
    options {
        timestamps()
    }

    environment {
        //Use Pipeline Utility Steps plugin to read information from pom.xml into env variables
        IMAGE = readMavenPom().getArtifactId()
        VERSION = readMavenPom().getVersion()
    }

    stages {
        stage('Build') {
            agent {
                docker {
                    /*
                     * Reuse the workspace on the agent defined at top-level of Pipeline but run inside a container.
                     * In this case we are running a container with maven so we don't have to install specific versions
                     * of maven directly on the agent
                     */
                    reuseNode true
                    image 'maven:3.5.0-jdk-8'
                }
            }
            steps {
                // using the Pipeline Maven plugin we can set maven configuration settings, publish test results, and annotate the Jenkins console
                withMaven(options: [findbugsPublisher(), junitPublisher(ignoreAttachments: false)]) {
                    sh 'mvn clean findbugs:findbugs package'
                }
            }
            post {
                success {
                    // we only worry about archiving the jar file if the build steps are successful
                    archiveArtifacts(artifacts: '**/target/*.jar', allowEmptyArchive: true)
                }
            }
        }

        stage('Quality Analysis') {
            parallel {
                // run Sonar Scan and Integration tests in parallel. This syntax requires Declarative Pipeline 1.2 or higher
                stage('Integration Test') {
                    agent any  //run this stage on any available agent
                    steps {
                        echo 'Run integration tests here...'
                    }
                }
                stage('Sonar Scan') {
                    agent {
                        docker {
                            // we can use the same image and workspace as we did previously
                            reuseNode true
                            image 'maven:3.5.0-jdk-8'
                        }
                    }
                    environment {
                        //use 'sonar' credentials scoped only to this stage
                        SONAR = credentials('sonar')
                    }
                    steps {
                        sh 'mvn sonar:sonar -Dsonar.login=$SONAR_PSW'
                    }
                }
            }
        }

        stage('Build and Publish Image') {
            when {
                branch 'master'  //only run these steps on the master branch
            }
            steps {
                /*
                 * Multiline strings can be used for larger scripts. It is also possible to put scripts in your shared library
                 * and load them with 'libaryResource'
                 */
                script {
                    COMMIT_ID=sh(returnStdout:true,script:'git rev-parse HEAD')
                    IMAGE_TAG="Jenkins-${env.BUILD_ID}_${BRANCH_NAME}_{COMMIT_ID}"
                	sh "docker login -u=$REGISTRY_AUTH_USR -p=$REGISTRY_AUTH_PSW ${env.REGISTRY_ADDRESS}"
                	sh "docker build . -t ${repository}:${IMAGE_TAG}"
                	sh "docker push ${repository}:${IMAGE_TAG}"
                }
            }
        }
        
        stage('Deploy') //calling webhook passing docker image details
        {
    		when {
    		    anyOf {
    		        branch 'develop';
    		        branch 'master';
    		    }
    		}
    		steps {
    		    script {
    		        REQUEST_BODY="""
    		        {
    					"artifacts": [ 
    					{
    						"type" : "docker/image",
    						"name" : "${repository}",
    						"reference" : "${repository}:${IMAGE_TAG}",
    						"version" : "${IMAGE_TAG}.trim()"
						}
    					]
					}"""
    		        
    		    }
				httpRequest consoleLogResponseBody: true, contentType: 'APPLICATION_JSON', httpMode: 'POST', requestBody: REQUEST_BODY, 
				url: "https://${host}", validResponseCodes: '200'    		        		    
    		}
		}
    }

    post {
        failure {
            // notify users when the Pipeline fails
            mail to: 'team@example.com',
                    subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
                    body: "Something is wrong with ${env.BUILD_URL}"
        }
    }
}