#include <stdio.h>
#include <stdlib.h>

struct Array {
    int size;
    int capacity;
    int *pointer;

    void (*push)(struct Array *array, int value);
    int (*findIndexAt)(struct Array * array, int index);
};

void push(struct Array *array, int value) {
    if (array->size == array->capacity) {
        array->capacity *= 2;

        array->pointer = realloc(array->pointer, array->capacity * sizeof(int));
    }

    *(array->pointer + array->size) = value;
    array->size += 1;
}

int findIndexAt(struct Array * array, int index) {
    if (index < 0 || index >= array->size) {
        return -1;
    }

    return array->pointer[index];
}

struct Array *createArray() {
    struct Array *array = malloc(sizeof(struct Array));

    array->size = 0;
    array->capacity = 2;

    array->pointer = malloc(array->capacity * sizeof(int));

    array->findIndexAt = findIndexAt;
    array->push = push;

    return array;
}

int main() {
    struct Array *pArray = createArray();

    printf("%p", pArray->pointer);

    printf(
    "size: %d\n capacity: %d\n index at(0): %d\n",
        pArray->size,
        pArray->capacity,
        pArray->findIndexAt(pArray, 0)
    );

    for (int i = 0; i < 20; ++i) {
        pArray->push(pArray, i);
    }

    printf(
        "size: %d\n capacity: %d\n index at(6): %d\n",
        pArray->size,
        pArray->capacity,
        pArray->findIndexAt(pArray, 6)
    );

    return 0;
}