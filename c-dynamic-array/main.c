#include <stdio.h>
#include <stdlib.h>

struct Array {
    int size;
    int capacity;
    int *pointer;

    void (*push)(struct Array *array, int value);

    int (*at)(struct Array *array, int index);

    void (*dump)(struct Array *array);

    int (*pop)(struct Array *array);
};

void dump(struct Array *array) {
    printf("arr(%d)[", array->size);

    for (int i = 0; i < array->size; ++i) {
        if (i == array->size - 1) {
            printf("%d", array->pointer[i]);
        } else {
            printf("%d, ", array->pointer[i]);
        }
    }

    printf("]\n");
}

void push(struct Array *array, int value) {
    if (array->size == array->capacity) {
        array->capacity *= 2;

        array->pointer = realloc(array->pointer, array->capacity * sizeof(int));
    }

    *(array->pointer + array->size) = value;
    array->size += 1;
}

int at(struct Array *array, int index) {
    if (index < 0 || index >= array->size) {
        return -1;
    }

    return array->pointer[index];
}

int pop(struct Array *array) {
    if (array->size == 0) return -1;

    int item = array->pointer[array->size - 1];
    array->size -= 1;

    return item;
}

struct Array *createArray() {
    struct Array *array = malloc(sizeof(struct Array));

    array->size = 0;
    array->capacity = 2;

    array->pointer = malloc(array->capacity * sizeof(int));

    array->at = at;
    array->push = push;
    array->dump = dump;
    array->pop = pop;

    return array;
}

int main() {
    struct Array *array = createArray();

    array->dump(array);

    for (int i = 0; i < 20; ++i) {
        array->push(array, i);
    }

    array->dump(array);

    printf("%d\n", array->pop(array));

    array->dump(array);

    array->push(array, 20);

    array->dump(array);

    return 0;
}