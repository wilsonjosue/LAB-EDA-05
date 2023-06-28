package eda.teo.pkg04;
import myExceptions.ExceptionNoFound;

public class Avl<E extends Comparable<E>> {

	private NodeAvl<E> root;
    private boolean height;

    public Avl() {
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public void insert(E x) throws ExceptionNoFound {
        this.root = insert(x, this.root);
        this.height = false;
    }

    private NodeAvl<E> insert(E x, NodeAvl<E> current) throws ExceptionNoFound {
        NodeAvl<E> res = current;
        if (current == null) {
            res = new NodeAvl<E>(x);
            this.height = true;
        } else {
            int resC = current.getData().compareTo(x);
            if (resC == 0)
                throw new ExceptionNoFound("Elemento ya se encuentra en el arbol");
            if (resC < 0) {
                res.setRight(insert(x, current.getRight()));
                if (this.height) {
                    switch (res.getBf()) {
                        case -1:
                            res.setBf(0);
                            this.height = false;
                            break;
                        case 0:
                            res.setBf(1);
                            break;
                        case 1:
                            res = balanceToLeft(res);
                            this.height = false;
                            break;
                    }
                }
            } else {
                res.setLeft(insert(x, current.getLeft()));
                if (this.height) {
                    switch (res.getBf()) {
                        case 1:
                            res.setBf(0);
                            this.height = false;
                            break;
                        case 0:
                            res.setBf(-1);
                            break;
                        case -1:
                            res = balanceToRight(res);
                            this.height = false;
                            break;
                    }
                }
            }
        }
        return res;
    }
    
    private NodeAvl<E> balanceToLeft(NodeAvl<E> node) {
        NodeAvl<E> son = node.getRight();
        if (son.getBf() == 1) {
            node.setBf(0);
            son.setBf(0);
            node = rotateRSL(node);
        } else if (son.getBf() == -1) {
            NodeAvl<E> gSon = son.getLeft();
            switch (gSon.getBf()) {
                case -1:
                    node.setBf(0);
                    son.setBf(-1);
                    break;
                case 0:
                    node.setBf(0);
                    son.setBf(0);
                    break;
                case 1:
                    node.setBf(1);
                    son.setBf(0);
                    break;
            }
            gSon.setBf(0);
            node.setRight(rotateRSR(son));
            node = rotateRSL(node);
        }
        return node;
    }

    private NodeAvl<E> balanceToRight(NodeAvl<E> node) {
        NodeAvl<E> son = node.getLeft();
       if (son.getBf() == -1) {
            node.setBf(0);
            son.setBf(0);
            node = rotateRSR(node);
        } 
		else if (son.getBf() == 1) {
            NodeAvl<E> gSon = son.getRight();
            switch (gSon.getBf()) {
                case 1:
                    node.setBf(0);
                    son.setBf(1);
                    break;
                case 0:
                    node.setBf(0);
                    son.setBf(0);
                    break;
                case -1:
                    node.setBf(-1);
                    son.setBf(0);
                    break;
           }
           gSon.setBf(0);
           node.setLeft(rotateRSL(son));
            node = rotateRSR(node);
        }
        return node;
    }

	private NodeAvl<E> rotateRSL(NodeAvl<E> node) {
        NodeAvl<E> son = node.getRight();
        node.setRight(son.getLeft());
        son.setLeft(node);
        node = son;
        return node;
    }

    private NodeAvl<E> rotateRSR(NodeAvl<E> node) {
        NodeAvl<E> son = node.getLeft();
        node.setLeft(son.getRight());
        son.setRight(node);
        node = son;
        return node;
    }

	public E getRoot() {
        return this.root.getData();
    }

    public E search(E x) throws ExceptionNoFound {
        NodeAvl<E> aux = search(x, this.root);
        if (aux == null)
            throw new ExceptionNoFound("Elemento no se encuentra en el arbol");
        return aux.getData();
    }

    private NodeAvl<E> search(E x, NodeAvl<E> current) throws ExceptionNoFound {
        if (current == null) {
            return null;
        } else {
            int resC = current.getData().compareTo(x);
            if (resC == 0)
                return current;
            if (resC < 0)
                return search(x, current.getRight());
            else
                return search(x, current.getLeft());
        }
    }

    public void remove(E x) throws ExceptionNoFound {
        this.root = remove(x, this.root);
    }

    private NodeAvl<E> remove(E x, NodeAvl<E> current) throws ExceptionNoFound {
        if (current == null)
            throw new ExceptionNoFound("Elemento no se encuentra en el arbol");

        int resC = current.getData().compareTo(x);

        if (resC < 0) {
            current.setRight(remove(x, current.getRight()));
        } else if (resC > 0) {
            current.setLeft(remove(x, current.getLeft()));
        } else {
            if (current.getLeft() == null && current.getRight() == null) {
                current = null;
            } else if (current.getLeft() == null) {
                current = current.getRight();
            } else if (current.getRight() == null) {
                current = current.getLeft();
            } else {
                NodeAvl<E> minValue = findMinValue(current.getRight());
                current.setData(minValue.getData());
                current.setRight(remove(minValue.getData(), current.getRight()));
            }
        }

        return current;
    }

    private NodeAvl<E> findMinValue(NodeAvl<E> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    public void inOrden() {
        if (isEmpty()) {
            System.out.println("Arbol esta vacío ....");
        } else {
            inOrden(this.root);
            System.out.println();
        }
    }

    private void inOrden(NodeAvl<E> current) {
        if (current.getLeft() != null)
            inOrden(current.getLeft());
        System.out.print(current + ", ");
        if (current.getRight() != null)
            inOrden(current.getRight());
    }
}
