package main.models

enum class Result(val point: Int) {
    WIN(2),
    TIE(1),
    DEFEAT(0),
    UNKNOWN(-1);
}