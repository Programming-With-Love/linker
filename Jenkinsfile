pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        echo 'start linker'
        sh '''rm -rf /usr/local/github/linker

cp -r /root/.jenkins/workspace/linker_master /usr/local/github/linker

cd /usr/local/github/linker

go build

go run main.go'''
      }
    }
  }
}