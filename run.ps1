javac -d out src\Main.java src\StudyPlanner.java src\model\Topic.java src\dsa\*.java
if ($LASTEXITCODE -eq 0) {
    java -cp out Main
}
