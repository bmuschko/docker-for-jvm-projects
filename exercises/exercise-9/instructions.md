# Exercise 9

In this exercise, you will put together a build pipeline with Jenkins.

## Creating a Jenkinsfile

In this step, you'll create and implement the `Jenkinsfile` to represent your build pipeline. Feel free to use an IDE of your choice.

1. In the root directory of the forked repository, create a new file named `Jenkinsfile`.
2. Add the file to SCM.
3. Create a new declarative pipeline in `Jenkinsfile` using the pipeline DSL. You can find the [pipeline syntax reference](https://jenkins.io/doc/book/pipeline/syntax/) in the Jenkins documentation.
4. As part of your pipeline definition, create a single-step stage called `Build`. The step should run the shell script `./gradlew` (Linux/Unix/MacOSX) or the batch file `gradlew.bat` (Windows) with the parameters `clean build`. The full command compiles the code and runs the tests. In a Windows setting, the full command would look like this: `bat 'gradlew.bat clean build'`.
5. Collect the test results with the expression `**/target/surefire-reports/TEST-*.xml` (Maven) or `**/build/test-results/test/TEST-*.xml` (Gradle) as a post-build operation so that they can be rendered in the Jenkins job.
6. Commit and push the `Jenkinsfile` to the `master` branch of your forked repository.

## Configuring a Jenkins pipeline

With the `Jenkinsfile` in place, we'll want to create a pipeline job in Jenkins.

1. Start Jenkins with `java -jar jenkins.war`. Please refer to the Jenkins documentation to learn about the [startup procedure](https://wiki.jenkins.io/display/JENKINS/Starting+and+Accessing+Jenkins) for your operating system. The default URL points to `http://localhost:8080/`.
2. Verify that the pipeline plugin has been installed under _Manage Jenkins > Manage Plugins > Installed > Search for "Pipeline"_. Install the plugin if it hasn't been installed.
3. Create a new Jenkins job via _New Item > Pipeline_. Enter a name and press the _OK_ button.
4. In the section _Pipeline_, _select Pipeline from SCM_. Select your forked repository HTTPS URL under _SCM > Git_.
5. Save the job.
6. Press the button _Build Now_ to execute the pipeline. Jenkins should check out the code, execute the "Build" step.
7. Have a look at the console output while the job is running. You should see that the build is run. The job should complete after a couple of seconds.
8. Inspect the pipeline view. Do you see your defined stage there?

## Enhancing the pipeline

In this step, you will enhance the existing pipeline definition.

1. Instead of defining a single stage, we'll want to split up the definition into multiple stages. Rename the existing stage to "Compile" and call the script `./mvnw clean compile` or `./gradlew clean compileJava`.
2. Create a new stage named "Build And Push Image" that follows the "Compile" stage. Call the script `./mvnw dockerBuild` or `./gradlew jibDockerBuild`.
3. Create a new stage named "Test" that follows the "Build And Push Image" stage. Call the script `./mvnw test` or `./gradlew test`.
4. Commit and push the `Jenkinsfile` to your repository.
5. Trigger a new build in Jenkins.
6. Inspect the pipeline view. Do you see the new stages?