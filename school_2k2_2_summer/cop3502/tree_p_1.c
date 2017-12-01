int count_leaf_nodes(struct treeNode *p){
	int cnt=0;
	
	if(p->left == NULL && p>right == NULL){
		++cnt;
	}
	else{
		count_leaf_nodes(p->left);
		count_leaf_nodes(p->right);
	}
	return cnt;
}
