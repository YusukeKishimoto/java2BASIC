package newlang4;

import java.util.HashSet;
import java.util.Set;

public class BlockNode extends Node{
	
	Node body;
	
	private BlockNode(Environment env){
		super.env = env;
		super.type = NodeType.BLOCK;
	}	
	private static Set<LexicalType> firstSet = new HashSet<LexicalType>();
	
	static{
		firstSet.add(LexicalType.IF);
		firstSet.add(LexicalType.WHILE);
		firstSet.add(LexicalType.DO);
		
	}
	public static Node isMatch(Environment env, LexicalUnit first){
		if(!firstSet.contains(first.type)){
			return null;
		}
		return new BlockNode(env);
	}
	@Override
	public boolean Parse() throws Exception{
		LexicalUnit lu = env.getInput().get();
		env.getInput().unget(lu);
		
		body = IfNode.isMatch(env, lu);
		if(body != null){
			return body.Parse() ;
		}
		
		body = LoopNode.isMatch(env, lu);
		if(body != null){
			return body.Parse();
		}
		
		return false;
	}
	@Override
	public Value getValue(){
		return body.getValue();
	}
}
