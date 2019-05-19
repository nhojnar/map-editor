//import java.util.ArrayList;
//
//import maphandler.MapHandler;
//import maphandler.TerrainType;
//import maphandler.Tile;
//import processing.core.*;
//
//public class Main_old extends PApplet
//{
//	public static void main(String[] args) 
//	{
//		PApplet.main("Main");
//	}
//
//	public ArrayList<ArrayList<Tile>> grid;
//	int gridScale;
//	TerrainType currentTerrain;
//	PVector start, end;
//
//	String fileName;
//
//	String clickMode;
//	final int EDITOR = 0, EXPORT_BOX = 1, IMPORT_BOX = 2;
//	int state, circleSize;
//
//	//Button test;
//
//	public void settings()
//	{
//		size(600, 600);
//	}
//
//	public void setup()
//	{
//		frameRate(60);
//		gridScale = 20;
//
//		currentTerrain = TerrainType.GRASS;
//		clickMode = "Single";
//		state = EDITOR;
//		fileName = "";
//		circleSize = 3;
//
//		//Action testAction = (test) -> { println("test");};
//		//test = new Button(550, 50, "Test", testAction);
//
//		initiateBlankMap();
//	}
//
//	public void keyPressed()
//	{
//		if(state == EXPORT_BOX)
//		{
//			if(key == ENTER)
//			{
//				MapHandler.exportMap(fileName, this);
//				fileName = "";
//				state = EDITOR;
//			}
//			else if(Character.isLetter(key)) fileName += key;
//		}
//		else if(state == IMPORT_BOX)
//		{
//			if(key == ENTER)
//			{
//				try{MapHandler.importMap(fileName, this);}
//				catch(Exception e)
//				{
//					println(e.getMessage());
//				}
//				fileName = "";
//				state = EDITOR;
//			}
//			else if(Character.isLetter(key)) fileName += key;
//		}
//		else if(state == EDITOR)
//		{
//			if(key == 's')
//				clickMode = "Single";
//			if(key == 'd')
//				clickMode = "Drag";
//			if(key == 'c')
//				clickMode = "Circle";
//			if(key == '0')
//				currentTerrain = TerrainType.WATER;
//			if(key == '1')
//				currentTerrain = TerrainType.GRASS;
//			if(key == '2')
//				currentTerrain = TerrainType.STONE;
//			if(key == '3')
//				currentTerrain = TerrainType.SAND;
//
//			if(key == '!')
//				circleSize = 1;
//			if(key == '@')
//				circleSize = 2;
//			if(key == '#')
//				circleSize = 3;
//			if(key == '$')
//				circleSize = 4;
//			if(key == '%')
//				circleSize = 5;
//			if(key == '^')
//				circleSize = 6;
//
//			if(key == 'E')
//				state = EXPORT_BOX;
//		}
//		if(key == 'I')
//			state = IMPORT_BOX;
//
//	}
//
//	public void mouseClicked()
//	{
//
//	}
//
//	public void mousePressed()
//	{
//		if(clickMode == "Drag" && state == EDITOR)
//		{
//			start = new PVector(round(mouseX/gridScale), round(mouseY/gridScale));
//			tileAt(start).terrainType = TerrainType.TEMP;
//		}
//	}
//
//	public void mouseReleased()
//	{
//		if(clickMode == "Drag" && state == EDITOR)
//		{
//			end = new PVector(round(mouseX/gridScale), round(mouseY/gridScale));
//
//			int inc = (start.x > end.x) ? 1 : -1;
//			for(int i = (int)end.x; i != (int)start.x + inc; i += inc)
//			{
//				int jinc = (start.y > end.y) ? 1 : -1;
//				for(int j = (int)end.y; j != (int)start.y + jinc; j += jinc)
//				{
//					tileAt(i,j).terrainType = currentTerrain;
//				}
//			}
//		}
//		if(clickMode == "Circle" && state == EDITOR)
//		{
//			PVector pos = new PVector(round(mouseX/gridScale), round(mouseY/gridScale));
//			for(int i = 0; i < grid.size(); i++)
//			{
//				for(int j = 0; j < grid.get(0).size(); j++)
//				{
//					if(dist(pos, tileAt(i,j).pos) <= circleSize)
//					{
//						tileAt(i,j).terrainType = currentTerrain;
//					}
//				}
//			}
//		}
//
//		if(clickMode == "Single" && state == EDITOR)
//		{
//			PVector pos = new PVector(round(mouseX/gridScale), round(mouseY/gridScale));
//			tileAt(pos).terrainType = currentTerrain;
//		}
//		if(state == EXPORT_BOX)
//		{
//			state = EDITOR;
//			fileName = "";
//		}
//	}
//
//
//	void initiateBlankMap()
//	{
//		grid = new ArrayList<ArrayList<Tile>>();
//		for(int i = 0; i < width/gridScale; i++)
//		{
//			grid.add(new ArrayList<Tile>());
//			for(int j = 0; j < height/gridScale; j++)
//			{
//				grid.get(i).add(new Tile(i,j,TerrainType.GRASS, this));
//			}
//		}
//	}
//
//	public void draw() 
//	{
//
//
//
//		translate(gridScale/2,gridScale/2);
//		background(210);
//		for (int i = 0; i < grid.size(); i++)
//		{
//			for(int j = 0; j < grid.get(i).size(); j++)
//			{
//				Tile tile = tileAt(i,j);
//
//				tile.update();
//			}
//		}
//
//		switch(currentTerrain)
//		{
//		case GRASS:
//			fill(0, 255, 0, 155);
//			break;
//		case STONE:
//			fill(210, 155);
//			break;
//		case WATER:
//			fill(0, 180, 255, 155);
//			break;
//		case SAND:
//			fill(240, 230, 90);
//			break;
//		case TEMP:
//			fill(255, 255, 0);
//			break;
//		}
//		rect(30, 10, 50, 30);
//
//		fill(0);
//		text(clickMode, 80, 5);
//
//		if(state == EXPORT_BOX || state == IMPORT_BOX)
//		{
//			fill(0);
//			text(fileName, 180, 5);
//		}
//		if(clickMode == "Circle")
//		{
//			fill(0);
//			text("Size: " + circleSize, 250, 5);
//		}
//	}
//
//	public Tile tileAt(float x, float y)
//	{
//		return grid.get(constrain((int)x, 0, grid.size()-1)).get(constrain((int)y, 0, grid.get(0).size()-1));
//	}
//
//	public Tile tileAt(PVector pos)
//	{
//		return tileAt(pos.x, pos.y);
//	}
//
//	double dist(PVector pos1, PVector pos2)
//	{
//		return dist(pos1.x,pos1.y,pos2.x,pos2.y);
//	}
//
//
//
//
//
//}
