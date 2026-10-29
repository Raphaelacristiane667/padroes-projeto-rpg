# Compila e roda o RPG. Procura um JDK no PATH, no JAVA_HOME ou em ~/.mvc-java-tools/jdk.
$ErrorActionPreference = "Stop"
Set-Location $PSScriptRoot

$javac = Get-Command javac -ErrorAction SilentlyContinue
if (-not $javac) {
    $candidatos = @()
    if ($env:JAVA_HOME) { $candidatos += $env:JAVA_HOME }
    $candidatos += Get-ChildItem (Join-Path $env:USERPROFILE ".mvc-java-tools\jdk") -Directory -ErrorAction SilentlyContinue |
        ForEach-Object { $_.FullName }
    $jdk = $candidatos | Where-Object { Test-Path (Join-Path $_ "bin\javac.exe") } | Select-Object -First 1
    if (-not $jdk) {
        Write-Host "Nenhum JDK encontrado. Instale o JDK 17 ou mais novo (https://adoptium.net) e rode de novo."
        exit 1
    }
    $env:Path = (Join-Path $jdk "bin") + ";" + $env:Path
    Write-Host "Usando o JDK em $jdk"
}

$fontes = (Get-ChildItem -Recurse src -Filter *.java).FullName
javac -encoding UTF-8 -d out $fontes
java "-Dstdout.encoding=UTF-8" -cp out br.com.rpg.Main
