## History

Author: Rahul Kumar Pamidi

Version History:
- 0.1 Initial Version

## Installation

### In Eclipse

This assumes you have Eclipse, m2e Maven plugin, Maven, and Git installed.

- Eclipse can be downloaded at: http://download.eclipse.org
- Maven can be downloaded at: http://maven.apache.org/download.html
- m2e Maven plugin comes with Eclipse and can be installed for older versions by going to Help > Eclipse Marketplace and typing 'm2e'
- Git can be downloaded at: http://git-scm.com/download

 It is recommended you set up your environment in the following manner:
 
 1. Download the source code from the below git repo.
    https://github.com/Rahul-20/IOCLAutomation.git
  
 2. Ensure the "maven" executable is in your path. Type "mvn" on the command line if it's been correctly added to your path:
        $ mvn -v
        Apache Maven 3.3.9 (bb52d8502b132ec0a5a3f4c09453c07478323dc5; 2015-11-10T22:11:47+05:30)
        Java version: 1.8.0_131, vendor: Oracle Corporation
        Java home: C:\Program Files\Java\jdk1.8.0_131\jre
        Default locale: en_US, platform encoding: Cp1252
        OS name: "windows 7", version: "6.1", arch: "amd64", family: "dos"
        
  3. Clean the project as below mentioned.
        $mvn clean  
        [INFO] Scanning for projects...
        [INFO]
        [INFO] ------------------------------------------------------------------------
        [INFO] Building IoclAutomation 1.0
        [INFO] ------------------------------------------------------------------------
        [INFO]
        [INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ IOCLAutomation ---
        [INFO] Deleting C:\IOCLAutomation\IoclAutomation\target
        [INFO] ------------------------------------------------------------------------
        [INFO] BUILD SUCCESS
        [INFO] ------------------------------------------------------------------------
  
  4. Build the project as below mentioned.
        $mvn install
        [INFO] Scanning for projects...
        [INFO]
        [INFO] ------------------------------------------------------------------------
        [INFO] Building IoclAutomation 1.0
        [INFO] ------------------------------------------------------------------------
        [INFO]
        [INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ IOCLAutoma
        tion ---
        [INFO] Using 'UTF-8' encoding to copy filtered resources.
        [INFO] Copying 5 resources
        [INFO]
        [INFO] --- maven-compiler-plugin:3.2:compile (default-compile) @ IOCLAutomation
        ---
        [INFO] Changes detected - recompiling the module!
        [INFO] Compiling 130 source files to C:\IOCLAutomation\IoclAutomation\target\cla
        sses
        [INFO]
        [INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ IO
        CLAutomation ---
        [INFO] Using 'UTF-8' encoding to copy filtered resources.
        [INFO] skip non existing resourceDirectory C:\IOCLAutomation\IoclAutomation\src\
        test\resources
        [INFO]
        [INFO] --- maven-compiler-plugin:3.2:testCompile (default-testCompile) @ IOCLAut
        omation ---
        [INFO] Nothing to compile - all classes are up to date
        [INFO]
        [INFO] --- maven-surefire-plugin:2.12.4:test (default-test) @ IOCLAutomation ---

        [INFO]
        [INFO] --- maven-war-plugin:2.2:war (default-war) @ IOCLAutomation ---
        [INFO] Packaging webapp
        [INFO] Assembling webapp [IOCLAutomation] in [C:\IOCLAutomation\IoclAutomation\t
        arget\IOCLAutomation]
        [INFO] Processing war project
        [INFO] Copying webapp resources [C:\IOCLAutomation\IoclAutomation\src\main\webap
        p]
        [INFO] Webapp assembled in [860 msecs]
        [INFO] Building war: C:\IOCLAutomation\IoclAutomation\target\IOCLAutomation.war
        [INFO] WEB-INF\web.xml already added, skipping
        [INFO]
        [INFO] --- maven-install-plugin:2.4:install (default-install) @ IOCLAutomation -
        [INFO] ------------------------------------------------------------------------
        [INFO] BUILD SUCCESS
        [INFO] ------------------------------------------------------------------------
       

Web Server:
Tomcat-version8

Frameworks Used:
Java,Spring Framework,Jersery Restful Framework,Hibernate ORM Framework,Mysql Database,SL4J logging framework

Versions: 
Java 1.7,Spring 4.1.0.RELEASE,Jersey 2.12,Hibernate 4.3.11.Final

Supported Users: 
SuperAdmin,Admin,TTES Operator,Supervisor

Supported Features:
BayMang,FanslipMang,UserMang,ContractorMang,LocationsMang,QunatityMang
