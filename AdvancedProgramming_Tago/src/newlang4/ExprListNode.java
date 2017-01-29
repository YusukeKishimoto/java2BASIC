package newlang4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExprListNode extends Node {

	Node body;
	List<Node> exprNodeList = new ArrayList<Node>();
	
	public ExprListNode(Environment env) {
		super.env = env;
		super.type = NodeType.EXPR_LIST;
	}
	
	private static Set<LexicalType> firstSet = new HashSet<LexicalType>();
	static {
		firstSet.add(LexicalType.SUB);			//-
		firstSet.add(LexicalType.LP);			// (
		firstSet.add(LexicalType.NAME);			// 変数
		firstSet.add(LexicalType.INTVAL);		// 整数定数
		firstSet.add(LexicalType.DOUBLEVAL);	// 小数定数
		firstSet.add(LexicalType.LITERAL);		// 文字列定数
	}
	
	public static Node isMatch(Environment env, LexicalUnit first) {
		if (!firstSet.contains(first.type)) {
			return null;
		}
		return new ExprListNode(env);
	}
	
	public List<Node> getExprNodeList(){
		return exprNodeList;
	}
	
	public void setExprNode(Node expr){
		exprNodeList.add(expr);
	}
	
	@Override
	public boolean Parse() throws Exception {
		LexicalUnit lu;
		while(true) {
			lu = env.getInput().get();			
			//System.out.println(lu);
			if(lu.getType() == LexicalType.NL){
				env.getInput().unget(lu);
				return true;
			}
			
			//,は読み飛ばしたい
			if (lu.getType() == LexicalType.COMMA){
				//System.out.print(", ");
				lu = env.getInput().get();	
			}
			body = ExprNode.isMatch(env, lu);
			if (body != null) {
				env.getInput().unget(lu);
				if (body.Parse() == false) return false; 
				exprNodeList.add(body);
				continue;
			}
			return false;
		}
	}

}