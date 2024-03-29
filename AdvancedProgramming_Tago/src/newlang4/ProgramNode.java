package newlang4;

public class ProgramNode extends Node{
	Environment env;
	Node stmt_list;
		
	public ProgramNode(Environment env) {
		this.env = env;
		this.type = NodeType.PROGRAM;
		// TODO Auto-generated constructor stub
	}
	//条件付きのnew 自分がファースト集合にマッチしたかどうかを考える?
	public static Node isMatch(Environment env, LexicalUnit first){
		return new ProgramNode(env);
	}
	
	@Override
	public boolean Parse() throws Exception{
		LexicalAnalyzer la = env.getInput();
		
		//first
		LexicalUnit lu = la.get();
		//firstを戻す
		env.getInput().unget(lu);
		
		stmt_list = StmtListNode.isMatch(env, lu);
		if (stmt_list == null) return false;
		if (stmt_list.Parse() == false) return false;
		if(env.getInput().get().getType() != LexicalType.END) return false;
		if (env.getInput().get().getType() != LexicalType.NL) return false;
		if (env.getInput().get().getType() != LexicalType.EOF) return false;
		
		return true;
	}
	
	@Override
	public Value getValue(){
		return stmt_list.getValue();
	}
}
