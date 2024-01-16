package com.example.todoSpringboot.entity;

import lombok.Data;

@Data
public class Pair<K, V> {
    private final K student_id;
    private final V lesson_info;

    public Pair(K key, V value) {
        this.student_id = key;
        this.lesson_info = value;
    }

    public K getKey() {
        return student_id;
    }

    public V getValue() {
        return lesson_info;
    }
}
