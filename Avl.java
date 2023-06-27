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
	
    private NodeAvl<E> insert(E x, NodeAvl<E> current) throws ExceptionNoFound{
	    NodeAvl<E> res = current;
	   if (current == null) {
	       res = new NodeAvl<E>(x);
	       this.height = true;
	    }
	    else {
	        int resC = current.getData().compareTo(x);
	        if (resC == 0)
		        throw new ExceptionNoFound("Elemento ya se encuentra en el arbol");
	        if (resC < 0) {
		        res.setRight(insert(x, current.getRight()));
	            if (this.height) {
		            switch(res.getBf()) {
		                case -1: res.setBf(0);
			                this.height = false;
			                break;
			            case 0: res.setBf(1);
			                break;
			            case 1: //res.setBf(2);
			                res = balanceToLeft(res);
			                this.height = false;
			                break;
		            }
                }	
	        }
	        else {
		        res.setLeft(insert(x, current.getLeft()));
		        /// completar la verificación de los factores de balance
	        }
        }
	    return res;	
    }

	private NodeAvl<E> balanceToLeft(NodeAvl<E> node){
		
	}
	
	private NodeAvl<E> rotateRSL(NodeAvl<E> node){

	}
	
	private NodeAvl<E> rotateRSR(NodeAvl<E> node){
		
	}

	public E getRoot() {
		return this.root.getData();
	}
	
	
	public E search(E x) throws ExceptionNoFound {
		
	}
	
	private NodeAvl<E> search(E x, NodeAvl<E> current) throws ExceptionNoFound{
		
		
	}
	
	//La eliminación debe ser modificada a efecto de verificar el balanceo del árbol.
	//Esta eliminación es del BST

	public void remove(E x) throws ExceptionNoFound {
		this.root = remove(x, this.root);	
	}
	
	private NodeAvl<E> remove(E x, NodeAvl<E> current) throws ExceptionNoFound{
		
			
	}
	
	private boolean isLeaf(NodeAvl<E> current) {
		
	}
	
	public void inOrden() {
		
	}
	
	
	
	private void inOrden(NodeAvl<E> current) {

	}

}
