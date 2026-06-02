package utilities;

public class Debug
{
	public static void msg(int anIntDebug, String aVariableName)
	{
		System.out.println(aVariableName  + ": "+ anIntDebug);
	}
	
	public static void msg(Vec2Int aVec2Debug)
	{
		
		System.out.println(aVec2Debug.toString()  + ": "+ aVec2Debug.X + " ; " + aVec2Debug.Y);	
	}
	
	public static void msg(Vec2Int aVec2Debug, String aVariableName)
	{
		
		System.out.println(aVariableName  + ": "+ aVec2Debug.X + " ; " + aVec2Debug.Y);	
	}
	
	public static void msg(String aDebugMessage)
	{
		System.out.println(aDebugMessage);
	}

}
