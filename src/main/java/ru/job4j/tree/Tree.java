package ru.job4j.tree;

import java.util.*;

class Tree<E> implements SimpleTree<E> {
    protected final Node<E> root;

    Tree(final E root) {
        this.root = new Node<>(root);
        size =1;
    }
    int size;

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> optionalParent = findBy(parent);
        Optional<Node<E>> optionalChild = findBy(child);
        if (optionalParent.isPresent() && !optionalChild.isPresent() ) {
            Node<E> el = optionalParent.get();
                 el.children.add(new Node<>(child));
                 size++;
                 rsl = true;
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}