package ace.y2012.week28;



public abstract class AST 
{

};

//class for numbers like 3.5, 10, +10, -10, .75
class NumberAST extends AST 
{
String Val;
public NumberAST(String val) 
{
	Val = val;
}
};

//class for identifier
class IdentifierAST extends AST {
String Name;
public IdentifierAST(String name)  
{
	Name = name;
}
};

//class for a binary operator, for example for a = b, where opertor is = and a is lhs, b is rhs
class BinaryAST extends AST 
{
enumTokenID Operator;
public AST LHS, RHS;

public BinaryAST(enumTokenID operator, AST lhs,  AST rhs) 
{
	Operator = operator; 
	LHS = lhs;
	RHS = rhs;
}
}

//class for a binary operator, for example for a = b, where opertor is = and a is lhs, b is rhs
class UnaryAST extends AST 
{
enumTokenID Operator;
public AST LHS;

public UnaryAST(enumTokenID operator, AST lhs) 
{
	Operator = operator; 
	LHS = lhs;
}
}

//class for function call like LOG10(x) or SIN(a+b*c)
class FunctionCallAST extends AST 
{
public AST Argument;
public String FunctionName;
public FunctionCallAST(String functionName, AST argument)  
{
	FunctionName = functionName;
	Argument = argument;
}
}

