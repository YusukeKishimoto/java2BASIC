package newlang4;

public class AssignNode extends Node{
	
	Node body;
	
	private AssignNode(Environment env){
		super.env = env;
		super.type = NodeType.ASSIGN_STMT;
	}
	
	public static Node isMatch(Environment env, LexicalUnit first){
		try{
			if(first.getType() != LexicalType.NAME) return null;
			
			LexicalUnit lu = env.getInput().get();
			lu = env.getInput().get();
			env.getInput().unget(first);
			env.getInput().unget(lu);
			{
			if(lu.getType() != LexicalType.EQ) return null;
			
			return new AssignNode(env);
			
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	@Override
	public boolean Parse() throws Exception{
		
	}

}
