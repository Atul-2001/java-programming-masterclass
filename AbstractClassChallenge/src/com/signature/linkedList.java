package com.signature;

public class linkedList implements NodeList {

    private ListItem root = null;

    public linkedList(ListItem root) {
        this.root = root;
    }

    public linkedList() {
        //empty constructor
    }

    @Override
    public ListItem getRoot() {
        return this.root;
    }

    @Override
    public boolean addItem(ListItem item) {
        if (this.root == null) {
            //The list is empty, so this item becomes the head of the list
            this.root = item;
            return true;
        } else {
            ListItem currentItem = this.root;
            while (currentItem != null) {
                int comparison = (currentItem.compareTo(item));

                if (comparison < 0) {
                    //New Item is greater, move right if possible
                    if (currentItem.next() != null) {
                        currentItem = currentItem.next();
                    } else {
                        //No next item exist, so insert it at end of list
                        currentItem.setNext(item).setPrevious(currentItem);
                        return true;
                    }
                } else if (comparison > 0) {
                    //New item is less, insert before
                    if (currentItem.previous() != null) {
                        currentItem.previous().setNext(item).setPrevious(currentItem.previous());
                        item.setNext(currentItem).setPrevious(item);
                    } else {
                        //the node with empty previous is the root
                        item.setNext(this.root).setPrevious(item);
                        this.root = item;
                    }
                    return true;
                } else {
                    //equal
                    System.out.println(item.getValue() + " is already present, not added.");
                    return false;
                }
            }
            //reached at end
            return false;
        }
    }

    @Override
    public boolean removeItem(ListItem item) {
        if (item != null) {
            System.out.println("Deleting item : " + item.getValue());
        }

        ListItem currentItem = this.root;
        while (currentItem != null) {
            int comparison = currentItem.compareTo(item);

            if (comparison == 0) {
                //found the item to delete
                if (currentItem == this.root) {
                    this.root = currentItem.next();
                } else {
                    currentItem.previous().setNext(currentItem.next());
                    if (currentItem.next() != null) {
                        currentItem.next().setPrevious(currentItem.previous());
                    }
                }
                return true;
            } else if (comparison < 0) {
                currentItem = currentItem.next();
            } else {
                //This item is greater than the item to be deleted
                //so the item is not in the list
                return false;
            }
        }
        //Reached at the end of the list without deleting item
        return false;
    }

    @Override
    public void traverse(ListItem root) {
        if (root == null) {
            System.out.println("The list is empty!");
        } else {
            int i = 0;
            while (root != null) {
                System.out.println("Item #" + ++i + " : " + root.getValue());
                root = root.next();
            }
        }
    }
}
