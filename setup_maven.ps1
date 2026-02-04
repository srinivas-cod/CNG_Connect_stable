$ErrorActionPreference = "Stop"
Write-Host "Setting up Maven and Java Environment..." -ForegroundColor Cyan

# 1. Setup Java Home
Write-Host "Configuring JAVA_HOME..."
try {
    # Get property from java -XshowSettings:properties
    $javaInfo = java -XshowSettings:properties -version 2>&1 
    $line = $javaInfo | Select-String "java.home"
    if (-not $line) {
        throw "Could not find java.home in output"
    }
    $javaHome = $line.ToString().Split("=")[1].Trim()
    
    Write-Host "Detected Java at: $javaHome"
    [Environment]::SetEnvironmentVariable("JAVA_HOME", $javaHome, "User")
    Write-Host "JAVA_HOME set permanently." -ForegroundColor Green
} catch {
    Write-Host "Error setting JAVA_HOME: $_" -ForegroundColor Red
}

# 2. Download Maven
$mavenVer = "3.9.6"
$mavenUrl = "https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/$mavenVer/apache-maven-$mavenVer-bin.zip"
$installDir = "$env:USERPROFILE\.maven"
$mavenHome = "$installDir\apache-maven-$mavenVer"
$zipPath = "$env:TEMP\maven.zip"

if (-not (Test-Path $mavenHome)) {
    Write-Host "Downloading Maven $mavenVer..."
    New-Item -ItemType Directory -Force -Path $installDir | Out-Null
    
    try {
        Invoke-WebRequest -Uri $mavenUrl -OutFile $zipPath
        Write-Host "Extracting Maven..."
        Expand-Archive -Path $zipPath -DestinationPath $installDir -Force
        Remove-Item $zipPath
        Write-Host "Maven installed to $mavenHome" -ForegroundColor Green
    } catch {
        Write-Host "Failed to download/install Maven. Please check your internet connection." -ForegroundColor Red
        exit 1
    }
} else {
    Write-Host "Maven already installed at $mavenHome"
}

# 3. Setup Path
Write-Host "Configuring Path..."
$currentPath = [Environment]::GetEnvironmentVariable("Path", "User")
$mavenBin = "$mavenHome\bin"

if ($currentPath -notlike "*$mavenBin*") {
    $newPath = "$currentPath;$mavenBin"
    [Environment]::SetEnvironmentVariable("Path", $newPath, "User")
    Write-Host "Added Maven to Path." -ForegroundColor Green
} else {
    Write-Host "Maven already in Path."
}

Write-Host "Setup Complete!" -ForegroundColor Green
Write-Host "IMPORTANT: You MUST close this window and open a new terminal for changes to take effect." -ForegroundColor Yellow
Write-Host "Press any key to exit..."
$null = $Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown")
