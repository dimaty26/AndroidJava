package ru.geekbrains.androidhw5.domain;

import androidx.annotation.NonNull;

public enum Operation {
   PLUS {
       @NonNull
       @Override
       public String toString() {return "+";}
   },
    MINUS {
        @NonNull
        @Override
        public String toString() {return "-";}
    },
    MULTIPLY {
        @NonNull
        @Override
        public String toString() {return "*";}
    },
    DIVISION {
        @NonNull
        @Override
        public String toString() {return "/";}
    },
    EMPTY {
        @NonNull
        @Override
        public String toString() {return "";}
    }
}
