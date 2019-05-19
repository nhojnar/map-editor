import maphandler.*;
import processing.core.*;
import java.util.ArrayList;
import gui.*;


public class Main extends PApplet
{
	enum ClickMode{
		SINGLE, DRAG, CIRCLE;
		public String getString()
		{
			switch(this)
			{
			case SINGLE:
				return "Single";
			case DRAG:
				return "Drag";
			case CIRCLE:
				return "Circle";
			default:
				return "Unknown";
			}
		}
	}
	
	public static void main(String[] args) 
	{
		PApplet.main("Main");
	}

	public Map map;
	TerrainType currentTerrain;
	PVector start, end;

	ClickMode clickMode;
	int circleSize;
	
	Gui gui;
	TextBox exportBox, importBox;
	Label exportLabel, importLabel, sizeLabel;
	Button singleMode, dragMode, circleMode, setGrass, setStone, setWater, setSand, sizeUp, sizeDown;

	public void settings()
	{
		size(800, 900);
	}

	public void setup()
	{
		frameRate(60);

		currentTerrain = TerrainType.WATER;
		clickMode = ClickMode.SINGLE;
		circleSize = 3;
		
		map = new Map(0, 0, 800, 800, 20, this);
		map.initiateBlankMap();
		
		gui = new Gui(0, 800, 800, 100, this);
		gui.setBackground(color(80, 210, 185));
		
		exportBox = new TextBox(100, 40, 150, 20, () -> exportFromBox());
		exportLabel = new Label(100, 25, 12, "Export Map");
		importBox = new TextBox(100, 80, 150, 20, () -> importFromBox());
		importLabel = new Label(100, 65, 12, "Import Map");
		
		singleMode = new Button(gui.w - 150, 20, 30, 30, "single.png", () -> setSingle(), this);
		dragMode = new Button(gui.w - 100, 20, 30, 30, "drag.png", () -> setDrag(), this);
		circleMode = new Button(gui.w - 50, 20, 30, 30, "circle.png", () -> setCircle(), this);
		
		setGrass = new Button(gui.w - 150, 60, 30, 30, "", () -> setGrass(), this);
		setStone = new Button(gui.w - 100, 60, 30, 30, "", () -> setStone(), this);
		setSand = new Button(gui.w - 50, 60, 30, 30, "", () -> setSand(), this);
		setWater = new Button(gui.w - 200, 60, 30, 30, "", () -> setWater(), this);
		
		sizeUp = new Button(280, 20, 30, 30, "plus.png", () -> sizeUp(), this);
		sizeDown = new Button(200, 20, 30, 30, "minus.png", () -> sizeDown(), this);
		sizeLabel = new Label(240, 30, 20, PApplet.str(circleSize));
		
		
		setGrass.setColor(color(0, 255, 0));
		setStone.setColor(color(210));
		setSand.setColor(color(240, 230, 90));
		setWater.setColor(color(0, 180, 255));
		
		GuiElement toAdd[] = {exportBox, exportLabel, importBox, importLabel, singleMode, dragMode, circleMode, sizeUp, sizeDown, setGrass, setStone, setSand, setWater, sizeLabel};
		gui.addElements(toAdd);
	}
	
	public void exportFromBox()
	{
		MapHandler.exportMap(map, exportBox.getText(), this);
		exportBox.clear();
	}
	public void importFromBox()
	{
		try {
		map = MapHandler.importMap(importBox.getText(), this);
		importBox.clear();
		} catch(NullPointerException e)
		{
			
		}
		
	}
	public void setSingle()
	{
		clickMode = ClickMode.SINGLE;
	}
	public void setCircle()
	{
		clickMode = ClickMode.CIRCLE;
	}
	public void setDrag()
	{
		clickMode = ClickMode.DRAG;
	}
	public void setGrass()
	{
		currentTerrain = TerrainType.GRASS;
	}
	public void setWater()
	{
		currentTerrain = TerrainType.WATER;
	}
	public void setStone()
	{
		currentTerrain = TerrainType.STONE;
	}
	public void setSand()
	{
		currentTerrain = TerrainType.SAND;
	}
	public void sizeUp()
	{
		circleSize += (circleSize >= 9) ? 0 : 1;
		sizeLabel.setLabel(PApplet.str(circleSize));
	}
	public void sizeDown()
	{
		circleSize -= (circleSize <= 1) ? 0 : 1;
		sizeLabel.setLabel(PApplet.str(circleSize));
	}
	public void keyPressed()
	{
		/*
		if(state == EXPORT_BOX)
		{
			if(key == ENTER)
			{
				MapHandler.exportMap(map, fileName, this);
				fileName = "";
				state = EDITOR;
			}
			else if(Character.isLetter(key) || Character.isDigit(key)) fileName += key;
		}
		else if(state == IMPORT_BOX)
		{
			if(key == ENTER)
			{
				try{MapHandler.importMap(fileName, this);}
				catch(Exception e)
				{
					println(e.getMessage());
				}
				fileName = "";
				state = EDITOR;
			}
			else if(Character.isLetter(key) || Character.isDigit(key)) fileName += key;
		}
		else if(state == EDITOR)
		{
			if(key == 's')
				clickMode = "Single";
			if(key == 'd')
				clickMode = "Drag";
			if(key == 'c')
				clickMode = "Circle";
			if(key == '0')
				currentTerrain = TerrainType.WATER;
			if(key == '1')
				currentTerrain = TerrainType.GRASS;
			if(key == '2')
				currentTerrain = TerrainType.STONE;
			if(key == '3')
				currentTerrain = TerrainType.SAND;

			if(key == '!')
				circleSize = 1;
			if(key == '@')
				circleSize = 2;
			if(key == '#')
				circleSize = 3;
			if(key == '$')
				circleSize = 4;
			if(key == '%')
				circleSize = 5;
			if(key == '^')
				circleSize = 6;

			if(key == 'E')
				state = EXPORT_BOX;
		}
		if(key == 'I')
			state = IMPORT_BOX;
		*/
		
		gui.typed(key);
	}

	public void mouseClicked()
	{

	}

	public void mousePressed()
	{
		if(map.mouseWithin(this))
		{
			if(clickMode == ClickMode.DRAG)
			{
				start = new PVector(round(mouseX/map.gridScale), round(mouseY/map.gridScale));
				map.tileAt(start).terrainType = TerrainType.TEMP;
			}
			if(clickMode == ClickMode.CIRCLE)
			{
				PVector pos = new PVector(round(mouseX/map.gridScale), round(mouseY/map.gridScale));
				for(int i = 0; i < map.mapWidth; i++)
				{
					for(int j = 0; j < map.mapHeight; j++)
					{
						if(dist(pos, map.tileAt(i,j).pos) <= circleSize)
						{
							map.tileAt(i,j).terrainType = currentTerrain;
						}
					}
				}
			}

			if(clickMode == ClickMode.SINGLE)
			{
				PVector pos = new PVector(round(mouseX/map.gridScale), round(mouseY/map.gridScale));
				map.tileAt(pos).terrainType = currentTerrain;
			}
		}
		else if(mouseX >= gui.pos.x && mouseX <= gui.pos.x + gui.w && mouseY >= gui.pos.y && mouseY <= gui.pos.y + gui.h)
		{
			gui.pressed();
		}
	}

	public void mouseReleased()
	{
		if(map.mouseWithin(this))
		{
			if(clickMode == ClickMode.DRAG)
			{
				int x = constrain(round((mouseX-map.x)/map.gridScale), 0, map.mapWidth -1);
				int y = constrain(round((mouseY-map.y)/map.gridScale), 0, map.mapHeight-1);
				end = new PVector(x,y);

				int inc = (start.x > end.x) ? 1 : -1;
				for(int i = (int)end.x; i != (int)start.x + inc; i += inc)
				{
					int jinc = (start.y > end.y) ? 1 : -1;
					for(int j = (int)end.y; j != (int)start.y + jinc; j += jinc)
					{
						map.tileAt(i,j).terrainType = currentTerrain;
					}
				}
			}
		}
	}


	public void draw() 
	{
		translate(map.gridScale/2,map.gridScale/2);
		background(210);

		map.update();


		
		translate(-map.gridScale/2, -map.gridScale/2);
		
		gui.update();
		
		
		stroke(130, 0, 20);
		strokeWeight(3);
		noFill();
		switch(clickMode)
		{
		case SINGLE:
			gui.rect(singleMode.pos.x, singleMode.pos.y, singleMode.w + 3, singleMode.h + 3);
			break;
		case DRAG:
			gui.rect(dragMode.pos.x, dragMode.pos.y, dragMode.w + 3, dragMode.h + 3);
			break;
		case CIRCLE:
			gui.rect(circleMode.pos.x, circleMode.pos.y, circleMode.w + 3, circleMode.h + 3);
			break;
		}
		
		
		switch(currentTerrain)
		{
		case GRASS:
			gui.rect(setGrass.pos.x, setGrass.pos.y, setGrass.w + 3, setGrass.h + 3);
			break;
		case STONE:
			gui.rect(setStone.pos.x, setStone.pos.y, setStone.w + 3, setStone.h + 3);
			break;
		case WATER:
			gui.rect(setWater.pos.x, setWater.pos.y, setWater.w + 3, setWater.h + 3);
			break;
		case SAND:
			gui.rect(setSand.pos.x, setSand.pos.y, setSand.w + 3, setSand.h + 3);
			break;
		case TEMP:
			break;
		}
	}

	double dist(PVector pos1, PVector pos2)
	{
		return dist(pos1.x,pos1.y,pos2.x,pos2.y);
	}
	
	





}
