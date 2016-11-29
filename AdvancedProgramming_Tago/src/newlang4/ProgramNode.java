package newlang4;

public class ProgramNode extends Node{
	Environment env;
	Node stmt_list;
		
	public ProgramNode(Environment env) {
		this.env = env;
		// TODO Auto-generated constructor stub
	}
	//条件付きのnew 自分がファースト集合にマッチしたかどうかを考える?
	public static Node isMatch(Environment env, LexicalUnit first){
		return new ProgramNode(env);
	}
	
	public boolean Parse() throws Exception{
		LexicalAnalyzer la = env.getInput();
		LexicalUnit lu = la.get();
		
		stmt_list = StmtListNode.isMatch(env, lu);
		if (stmt_list == null) return false;
		return stmt_list.Parse();

	}
}