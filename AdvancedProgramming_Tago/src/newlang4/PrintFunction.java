package newlang4;

public class PrintFunction extends Function{

	@Override
	public Value invoke(ExprListNode exprlist){
	
		for(Node node : exprlist.exprNodeList){
			//System.out.println(node.getValue().getType());
			if(node.getValue().getType() == ValueType.INTEGER) System.out.print(node.getValue().getIValue()+"\t");
			if(node.getValue().getType() == ValueType.DOUBLE) System.out.print(node.getValue().getDValue()+"\t");
			if(node.getValue().getType() == ValueType.STRING) System.out.print(node.getValue().getSValue()+"\t");
		}
		
		System.out.println();
		
		return null;
	}
}