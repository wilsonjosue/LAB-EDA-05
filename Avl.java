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
                // completar la verificación de los factores de balance
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
}
