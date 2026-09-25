#!/usr/bin/env bash
set -e

# --- Путь к вашему JDK (внутри должна быть папка bin/ с javac, javadoc, jar, java) ---
JDK="/c/Program Files/Java/jdk-21.0.12.1"

# Если JDK не найден по этому пути — попробуем найти автоматически
if [ ! -x "$JDK/bin/javadoc.exe" ] && [ ! -x "$JDK/bin/javadoc" ]; then
    for dir in "/c/Program Files/Java"/*/bin; do
        if [ -x "$dir/javadoc.exe" ] || [ -x "$dir/javadoc" ]; then
            JDK="$(dirname "$dir")"
            break
        fi
    done
fi

if [ ! -x "$JDK/bin/javadoc.exe" ] && [ ! -x "$JDK/bin/javadoc" ]; then
    echo "JDK не найден. Проверьте путь в переменной JDK."
    echo "Содержимое /c/Program Files/Java:"
    ls "/c/Program Files/Java" 2>/dev/null || echo "  (папки нет)"
    exit 1
fi

JAVAC="$JDK/bin/javac"
JAVADOC="$JDK/bin/javadoc"
JAR="$JDK/bin/jar"
JAVA="$JDK/bin/java"

echo "Используем JDK: $JDK"

# --- Настройки проекта ---
SRC_MAIN="src/main/java"
OUT="build/classes"
JARFILE="build/Task_1_1_1.jar"
MAIN="ru.nsu.aimykhelov.task111.Main"
DOCS="build/docs/javadoc"

# --- Сборка ---
rm -rf build
mkdir -p "$OUT"

echo "Компиляция..."
find "$SRC_MAIN" -name '*.java' > build/sources.txt
"$JAVAC" -encoding UTF-8 -d "$OUT" @build/sources.txt

echo "Javadoc..."
"$JAVADOC" -encoding UTF-8 -d "$DOCS" -Xdoclint:none -quiet @build/sources.txt

echo "Jar..."
"$JAR" --create --file "$JARFILE" --main-class "$MAIN" -C "$OUT" .

echo "Запуск..."
"$JAVA" -jar "$JARFILE"