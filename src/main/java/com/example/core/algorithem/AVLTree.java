package com.example.core.algorithem;

/**
 * -描述- AVL树--自平衡二叉查找树，又称高度平衡树
 * -AVL树本质上还是一棵二叉搜索树，它的特点是:
 * -1.本身首先是一棵二叉搜索树。
 * -2.带有平衡条件：每个结点的左右子树的高度之差的绝对值（平衡因子）最多为1
 * -也就是说，AVL树，本质上是带了平衡功能(自旋)的二叉查找树（二叉排序树，二叉搜索树）
 * 在插入的过程中，会出现四种情况破坏AVL树的特性，我们可以采取如下相应的旋转。
 *   1、左-左型：做右旋。
 *   2、右-右型：做左旋转。
 *   3、左-右型：先做左旋，后做右旋。
 *   4、右-左型：先做右旋，再做左旋。
 */
public class AVLTree<T extends Comparable<T>> {
    private TreeNode<T> root; //根节点

    /**
     * 获取树的高度
     */
    public int height(TreeNode<T> tree){
        if (tree == null) return 0;
        return tree.getHeight();
    }

    /**
     * 左-左型，向右旋转
     * k2 -- 根节点
     * k1 -- 旋转后根节点
     *
     * LL旋转是围绕"失去平衡的AVL根节点"进行的，也就是节点k2；而且由于是LL情况，即左左情况
     * 就用手抓着"左孩子，即k1"使劲摇。将k1变成根节点，k2变成k1的右子树，"k1的右子树"变成"k2的左子树"
     */
    private TreeNode<T> left_rotate(TreeNode<T> k2){
        TreeNode<T> k1;
        //把k2左孩子变成根节点(k1)
        k1 = k2.left;
        //k1的右子树变成k2的左子树
        k2.left = k1.right;
        //k2变成k1（根节点）的右子树
        k1.right = k2;
        //重新计算节点的高度
        k2.height = Math.max(height(k2.left),height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left),height(k1.right)) + 1;
        return k1;
    }

    /**
     * 右-右型，向左旋转
     * k2 -- 根节点
     * k1 -- 旋转后根节点
     */
    private TreeNode<T> right_rotate(TreeNode<T> k2){
        TreeNode<T> k1;
        //把k2的右孩子变成根节点
        k1 = k2.right;
        //把k1的左子树变成k2的右子树
        k2.right = k1.left;
        //把k2变成k1的左子树
        k1.left = k2;
        //重新计算节点的高度
        k2.height = Math.max(height(k2.left),height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left),height(k1.right)) + 1;
        return k1;
    }

    /**
     * 左-右型，分两步：
     * 1.(右-右型)先向左旋转 -- >变成左-左型
     * 2.（左-左型）-- >向右旋转
     */
    private TreeNode<T> left_right_rotate(TreeNode<T> k3){
        k3.left = right_rotate(k3.left);
        return left_rotate(k3);
    }

    /**
     * 右-左型,分两步：
     * 1.（左-左型）先向右旋转 -- >变成右-右型
     * 2. (右-右型) -- >向左旋转
     */
    private TreeNode<T> right_left_rotate(TreeNode<T> k3){
        k3.right = left_rotate(k3.right);
        return right_rotate(k3);
    }

    /**
     * 将结点插入到AVL树中，并返回根节点
     *
     * 参数说明：
     *     tree AVL树的根结点
     *     data 插入的结点的键值
     * 返回值：
     *     根节点
     */
    private TreeNode<T> insert(TreeNode<T> tree, T data){
        if(tree == null){
            //新建节点
            tree = new TreeNode<T>(data,null,null);
            if(tree == null){
                System.out.println("ERROR: create avltree node failed!");
                return null;
            }
        }else{
            int cmp = data.compareTo(tree.data);
            if(cmp < 0){ //应该将data插入到tree的左子树的情况
                tree.left = insert(tree.left,data);
                //插入节点后，若avl树失去平衡，则进行相应的调节
                if(height(tree.left) - height(tree.right) == 2){
                    if(data.compareTo(tree.left.data) < 0){ //画图实现一下，确定是 左-左型
                        tree = left_rotate(tree);
                    }else{ //否则就是左-右型
                        tree = left_right_rotate(tree);
                    }
                }
            }else if(cmp > 0){ //应该将data插入到tree的右子树的情况
                tree.right = insert(tree.right,data);
                //插入节点后，若avl树失去平衡，则进行相应的调节
                if(height(tree.right) - height(tree.left) == 2){
                    if(data.compareTo(tree.right.data) < 0){ //确定是 右-左型
                        tree = right_left_rotate(tree);
                    }else{ //否则是右-右型
                        tree = right_rotate(tree);
                    }
                }
            }else{ //cmp == 0
                System.out.println("添加失败，不允许添加相同的元素!");
            }
        }
        tree.height = Math.max(height(tree.left),height(tree.right)) + 1;
        return tree;
    }

    public void insert(T data){
        root = insert(root,data);
    }

    /**
     * 删除结点(z)，返回根节点
     *
     * 参数说明：
     *     tree AVL树的根结点
     *     z 待删除的结点
     * 返回值：
     *     根节点
     */
    private TreeNode<T> delete(TreeNode<T> tree, TreeNode<T> node){
        //如果根为空，或没有要删除的节点,返回null
        if(tree == null || node == null){
            return null;
        }
        TreeNode<T> temp;
        int cmp = node.data.compareTo(tree.data);
        if(cmp < 0){ //待删除的节点在tree的左子树中
            tree.left = delete(tree.left,node);
            //删除节点后，若avl树失去平衡，则进行相应的调整
            if(height(tree.right) - height(tree.left) == 2){
                temp = tree.right;
                if(height(temp.left) > height(temp.right)){ //右-左型
                    tree = right_left_rotate(tree);
                }else{ //右-右型
                    tree = right_rotate(tree);
                }
            }
        }else if (cmp > 0){ //待删除的节点在tree的右子树中
            tree.right = delete(tree.right,node);
            //删除节点后，若avl树失去平衡，则进行相应的调整
            if(height(tree.left) - height(tree.right) == 2){
                temp = tree.left;
                if(height(temp.left) > height(temp.right)){ //左-左型
                    tree = left_rotate(tree);
                }else{ //左-右型
                    tree = left_right_rotate(tree);
                }
            }
        }else { //tree是要删除的节点
            //如果tree的左右子树都不为空
            if(tree.left != null && tree.right != null){
                if(height(tree.left) > height(tree.right)){
                    //如果tree的左子树比右子树高；
                    //(01)找出tree的左子树中的最大节点
                    //(02)将该最大节点的值赋值给tree。
                    //(03)删除该最大节点。
                    //这类似于用"tree的左子树中最大节点"做"tree"的替身；
                    //采用这种方式的好处是：删除"tree的左子树中最大节点"之后，AVL树仍然是平衡的。
                    TreeNode<T> max = maximum(tree.left);
                    tree.data = max.data;
                    tree.left = delete(tree.left,max);
                }else {
                    //如果tree的左子树不比右子树高(即，它们相等，或右子树比左子树高1)
                    //找出tree的右子树中的最小节点
                    //将最小节点的值赋值给tree
                    //这类似于用"tree的右子树中最小节点"做"tree"的替身；
                    //采用这种方式的好处是：删除"tree的右子树中最小节点"之后，AVL树仍然是平衡的。
                    TreeNode<T> min = minimum(tree.right);
                    tree.data = min.data;
                    tree.right = delete(tree.right,min);
                }
            }else { //左子树，或右子树为空
                tree = (tree.left != null) ? tree.left:tree.right;
            }
        }
        return tree;

    }

    public void delete(TreeNode<T> node) {
        root = delete(root,node);
    }

    /**
     * 查找最大节点,返回avl树的最大节点
     */
    private TreeNode<T> maximum(TreeNode<T> tree){
        if(tree == null){
            return null;
        }
        while (tree.right != null){
            tree = tree.right;
        }
        return tree;
    }

    /**
     * 查找最小节点,返回avl树的最小节点
     */
    private TreeNode<T> minimum(TreeNode<T> tree){
        if(tree == null){
            return null;
        }
        while (tree.left!= null){
            tree = tree.left;
        }
        return tree;
    }

    /**
     * 前序遍历avl树
     */
    public void preOrder(TreeNode<T> tree){
        if(tree != null){
            System.out.print(tree.data + " ");
            preOrder(tree.left);
            preOrder(tree.right);
        }
    }

    /**
     * 中序遍历avl树
     */
    public void midOrder(TreeNode<T> tree){
        if(tree != null){
            midOrder(tree.left);
            System.out.print(tree.data + " ");
            midOrder(tree.right);
        }
    }

    /**
     * 后序遍历avl树
     */
    public void postOrder(TreeNode<T> tree){
        if(tree != null){
            postOrder(tree.left);
            postOrder(tree.right);
            System.out.print(tree.data + " ");
        }
    }


}

class TreeNode<T extends Comparable<T>>{
    T data;
    TreeNode<T> left; //左孩子
    TreeNode<T> right; //右孩子
    int height; //记录节点的高度,空的二叉树的高度是0

    public TreeNode(T data, TreeNode<T> left, TreeNode<T> right){
        this.left = left;
        this.right = right;
        this.data = data;
        this.height = 0;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
