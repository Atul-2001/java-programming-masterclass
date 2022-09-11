package com.signature;

public class BinarySearchTree implements NodeList {

    private ListItem root = null;

    public BinarySearchTree(ListItem root) {
        this.root = root;
    }

    @Override
    public ListItem getRoot() {
        return this.root;
    }

    @Override
    public boolean addItem(ListItem item) {
        if (this.root == null) {
            this.root = item;
            return true;
        } else {
            ListItem currentItem = this.root;

            while (currentItem != null) {
                int comparison = (currentItem.compareTo(item));

                if (comparison < 0) {
                    if (currentItem.next() != null) {
                        currentItem = currentItem.next();
                    } else {
                        currentItem.setNext(item);
                        return true;
                    }
                } else if (comparison > 0) {
                    if (currentItem.previous() != null) {
                        currentItem = currentItem.previous();
                    } else {
                        currentItem.setPrevious(item);
                        return true;
                    }
                } else {
                    System.out.println(item.getValue() + " is already present, Not adding.");
                    return false;
                }
            }
            return false;
        }
    }

    @Override
    public boolean removeItem(ListItem item) {
        if (this.root == null) {
            System.out.println("Tree is empty!");
        } else {
            if (item != null) {
                System.out.println("Deleting item : " + item.getValue());
            }

            ListItem currentItem = this.root;
            ListItem parentItem = currentItem;

            while (currentItem != null) {
                int comparison = (currentItem.compareTo(item));

               if (comparison < 0) {
                    parentItem = currentItem;
                    currentItem = currentItem.next();
                } else if (comparison > 0) {
                    parentItem = currentItem;
                    currentItem = currentItem.previous();
                } else {
                   performDeletion(currentItem, parentItem);
                   return true;
               }
            }
            return false;
        }
        return false;
    }

    private void performDeletion(ListItem item, ListItem parent) {
        if (item.next() == null) {
            if (parent.next() == item) {
                parent.setNext(item.previous());
            } else if (parent.previous() == item) {
                parent.setPrevious(item.previous());
            } else {
                this.root = item.previous();
            }
        } else if (item.previous() == null) {
            if (parent.next() == item) {
                parent.setNext(item.next());
            } else if (parent.previous() == item) {
                parent.setPrevious(item.next());
            } else {
                this.root = item.next();
            }
        } else {

            ListItem currentItem = item.next();
            ListItem leftParent= item;

            while (currentItem.previous() != null) {
                leftParent = currentItem;
                currentItem = currentItem.previous();
            }

            item.setValue(currentItem.getValue());

            if (leftParent == item) {
                System.out.println("LeftParent == item");
                item.setNext(currentItem.next());
            } else {
                leftParent.setPrevious(currentItem.next());
            }
        }
    }

    @Override
    public void traverse(ListItem root) {
        if (root != null) {
            traverse(root.previous());
            System.out.println(root.getValue());
            traverse(root.next());
        }
    }
}
