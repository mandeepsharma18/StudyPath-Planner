@echo off
javac -d out src\Main.java src\StudyPlanner.java src\model\Topic.java src\dsa\*.java
if %ERRORLEVEL% EQU 0 (
    java -cp out Main
)
