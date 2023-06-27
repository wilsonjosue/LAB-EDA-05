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
