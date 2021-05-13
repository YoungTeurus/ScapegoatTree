package implementations;

public class SimpleBinaryTree<T extends Comparable<T>> extends BaseBinaryTree<T> {
    public SimpleBinaryTree(){
        super();
    }

    public SimpleBinaryTree(BinaryTreeNode<T> headNode){
        super(headNode);
    }

    @Override
    protected void actionAfterRemove() {

    }
}
