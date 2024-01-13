#include <stdio.h>
#include <malloc.h>

struct Array {
    int size;
    int capacity;
    int *pointer;

//    void (*push)(struct Array *array, int value);
    int (*findIndexAt)(struct Array * array, int index);
};

void push(struct Array *array, int value) {
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

    array->size = 3;
    array->capacity = 4;

    array->pointer = malloc(array->capacity * sizeof(int));

    *(array->pointer + 0) = 10;
    *(array->pointer + 1) = 20;
    *(array->pointer + 2) = 30;

    array->findIndexAt = findIndexAt;

    return array;
}

int main() {
    struct Array *pArray = createArray();

    printf("%d\n", pArray->findIndexAt(pArray, 2));

    return 0;
}