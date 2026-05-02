# Java and Maven Setup Guide

## Download and Install JDK 21

### Download
1. Visit: https://adoptium.net/temurin/releases/?version=21
2. Select: Windows ¡ú x64 ¡ú JDK 21 (LTS)
3. Download the `.msi` file

### Install
1. Double-click the downloaded `.msi` file
2. Click Next through the wizard
3. Default installation path: `C:\Program Files\Eclipse Adoptium\jdk-21.0.x.x-hotspot`

### Configure Environment Variables
1. Press `Win + R`, type `sysdm.cpl`, press Enter
2. Click **Advanced** tab ¡ú **Environment Variables**
3. Under **System variables**, click **New**
   - Variable name: `JAVA_HOME`
   - Variable value: `C:\Program Files\Eclipse Adoptium\jdk-21.0.11.10-hotspot`
4. Find **Path** in System variables, double-click
5. Click **New**, add: `%JAVA_HOME%\bin`
6. Click OK to save all

### Verify
Open a new command prompt and run:
```bash
java -version
```

---

## Download and Install Maven

### Download
1. Visit: https://maven.apache.org/download.cgi
2. Download `apache-maven-3.9.9-bin.zip`

### Install
1. Extract the zip to `C:\Program Files\Apache\maven`
2. Create the folder if it doesn't exist

### Configure Environment Variables
1. Press `Win + R`, type `sysdm.cpl`, press Enter
2. Click **Advanced** tab ¡ú **Environment Variables**
3. Under **System variables**, click **New**
   - Variable name: `MAVEN_HOME`
   - Variable value: `C:\Program Files\Apache\maven`
4. Find **Path** in System variables, double-click
5. Click **New**, add: `%MAVEN_HOME%\bin`
6. Click OK to save all

### Verify
Open a new command prompt and run:
```bash
mvn -version
```

---

## Quick Commands

If you don't want to configure environment variables, use full paths:

```bash
# Run Maven
"C:\Program Files\Apache\maven\bin\mvn.cmd" spring-boot:run

# Set temporary JAVA_HOME (Windows PowerShell)
$env:JAVA_HOME='C:\Program Files\Eclipse Adoptium\jdk-21.0.11.10-hotspot'
$env:Path="$env:JAVA_HOME\bin;$env:Path"
```
