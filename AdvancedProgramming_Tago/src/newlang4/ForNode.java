package newlang4;

public class ForNode extends Node{
	
	Node assign;
	LexicalUnit intval;
	Node stmt_list;
	LexicalUnit nextname;
	
	private ForNode(Environment env){
		super.env = env;
	}
	
	public static Node isMatch(Environment env, LexicalUnit lu){
		if(lu.type == LexicalType.FOR){
			return new ForNode(env);
		}
		return null;
	}
	
	
	@Override
	public boolean Parse() throws Exception{
		//FOR
		LexicalUnit lu = env.getInput().get();
		//Assignの最初のユニット
		lu = env.getInput().get();
		env.getInput().unget(lu);
		
		assign = AssignNode.isMatch(env, lu);
		if (assign != null) {
			if(assign.Parse()==true){				
				lu = env.getInput().get();
				if(lu.type!=LexicalType.TO) return false;

				intval = env.getInput().get();
				if(intval.type != LexicalType.INTVAL) return false;

				lu = env.getInput().get();
				if(lu.type!=LexicalType.NL) return false;
				lu = env.getInput().get();
				env.getInput().unget(lu);

				stmt_list = StmtListNode.isMatch(env, lu);
				if(stmt_list != null){
					if(stmt_list.Parse() == true){						
						nextname = env.getInput().get();
						if(nextname.type!=LexicalType.NAME) return false;
						return true;
					}
				}
			}
		}
		return false;
	}
	@Override
	public Value getValue(){
		assign.getValue();
		AssignNode as = (AssignNode)assign;
		VariableNode va = (VariableNode)as.var;

		if(va.val.getIValue() > intval.value.getIValue()){
			return null;
		}
		if(va.val.getType() != ValueType.INTEGER && va.val.getType() != ValueType.DOUBLE){
			return null;
		}
		while(va.val.getIValue() <= intval.value.getIValue()){
	
			stmt_list.getValue();
			va.setValue(new ValueImpl(env.var_table.get(nextname.value.getSValue()).val.getIValue()+1));
			
		}
		return null;
	}
}
