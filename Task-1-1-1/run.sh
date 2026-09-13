#!/usr/bin/env bash
set -e

JDK="/c/Program Files/Java/jdk-21.0.12.1"

for tool in javac javadoc jar java; do
    if [ ! -x "$JDK/bin/$tool.exe" ] && [ ! -x "$JDK/bin/$tool" ]; then
        echo "Не найден $tool в $JDK/bin"
        exit 1
    fi
done

JAVAC="$JDK/bin/javac"
JAVADOC="$JDK/bin/javadoc"
JAR="$JDK/bin/jar"
JAVA="$JDK/bin/java"

echo "Используем JDK: $JDK"

SRC=src/main/java
OUT=build/classes
JARFILE=build/Task_1_1_1.jar
MAIN=ru.nsu.aimykhelov.Task_1_1_1.Main

rm -rf build
mkdir -p "$OUT"

echo "Компиляция..."
find "$SRC" -name '*.java' > build/sources.txt
"$JAVAC" -encoding UTF-8 -d "$OUT" @build/sources.txt

echo "Javadoc..."
"$JAVADOC" -encoding UTF-8 -d build/docs -Xdoclint:none -quiet @build/sources.txt

echo "Jar..."
"$JAR" --create --file "$JARFILE" --main-class "$MAIN" -C "$OUT" .

echo "Запуск..."
"$JAVA" -jar "$JARFILE"