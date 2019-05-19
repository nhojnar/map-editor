//import java.util.ArrayList;
//
//import maphandler.TerrainType;
//import maphandler.Tile;
//import processing.data.Table;
//
//public class MapHandler
//{
//
//	static void importMap(String mapName, Main app)
//	{
//		Table table = app.loadTable("maps.\\" + mapName + ".map", "csv");
//		int mapData[][] = new int[table.getRowCount()][table.getColumnCount()];
//		for(int i = 0; i < table.getRowCount(); i++)
//		{
//			for(int j = 0; j < table.getColumnCount(); j++)
//			{
//				mapData[i][j] = table.getInt(j, i);
//				//gridN.get(i).add(new Tile(i, j, Integer.parseInt(table.getString(j, i)),app));
//			}
//		}
//		app.grid.clear();
//		for(int i = 0; i < mapData.length; i++)
//		{
//			app.grid.add(new ArrayList<Tile>());
//			for(int j = 0; j < mapData[0].length; j++)
//			{
//				TerrainType thisType;
//				switch(mapData[i][j])
//				{
//				case 0:
//					thisType = TerrainType.WATER;
//					break;
//				case 1:
//					thisType = TerrainType.GRASS;
//					break;
//				case 2:
//					thisType = TerrainType.STONE;
//					break;
//				case 3:
//					thisType = TerrainType.SAND;
//					break;
//				default:
//					thisType = TerrainType.TEMP;
//				}
//				app.grid.get(i).add(new Tile(i, j, thisType, app));
//			}
//		}
//		System.out.printf("%s.map imported.\n",  mapName);
//	}
//
//	static void exportMap(String mapName, Main app)
//	{
//		Table mapData = new Table();
//		for(int j = 0; j < app.grid.get(0).size(); j++)
//		{
//			String row[] = new String[app.grid.size()];
//			for(int i = 0; i < app.grid.size(); i++)
//			{
//				row[i] = Main.str(app.grid.get(i).get(j).terrainType.terrainInt());
//			}
//			mapData.addRow(row);
//		}
//		app.saveTable(mapData, "maps.\\" + mapName + ".map", "csv");
//		System.out.printf("Map exported as %s.map\n",mapName);
//	}
//
//	static void basicMap(Main app)
//	{
//		app.grid = new ArrayList<ArrayList<Tile>>();
//		for(int i = 0; i < app.width/app.gridScale - 3; i++)
//		{
//			app.grid.add(new ArrayList<Tile>());
//			for(int j = 0; j < app.height/app.gridScale; j++)
//			{
//				float thirdWidth = app.width/app.gridScale/3;
//				float thirdHeight = app.height/app.gridScale/3;
//				if(i > thirdWidth && i < 2*thirdWidth && j > thirdHeight && j < 2*thirdHeight) app.grid.get(i).add(new Tile(i,j,TerrainType.STONE, app));
//				else app.grid.get(i).add(new Tile(i,j,TerrainType.GRASS, app));
//			}
//		}
//		for(int i = app.width/app.gridScale - 3; i < app.width/app.gridScale; i++)
//		{
//			app.grid.add(new ArrayList<Tile>());
//			for(int j = 0; j < app.height/app.gridScale; j++)
//			{
//				app.grid.get(i).add(new Tile(i,j,TerrainType.WATER, app));
//			}
//		}
//	}
//}