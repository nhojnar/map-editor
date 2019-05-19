//import maphandler.TerrainType;
//import processing.core.*;
//
//class Tile
//{
//  PVector pos;
//  TerrainType terrainType;
//  boolean occupied;
//  float maxFood, food, foodRegen;
//  Main app;
//  
//  Tile(int x, int y, TerrainType terrainType, Main app)
//  {    
//    this.pos = new PVector(x,y);
//    this.terrainType = terrainType;
//    this.occupied = false;
//    this.app = app;
//    switch(terrainType)
//    {
//      case GRASS:
//        this.maxFood = 25;
//        this.foodRegen = 1.2f;
//        break;
//      case STONE:
//        this.maxFood = 8;
//        this.foodRegen = .7f;
//        break;
//      case WATER:
//        this.maxFood = 0;
//        this.foodRegen = 0;
//        break;
//      case SAND:
//        this.maxFood = 1;
//        this.foodRegen = 0;
//        break;
//      default:
//    	this.maxFood = 0;
//    	this.foodRegen = 0;
//    	break;
//    }
//    this.food = this.maxFood;
//  }
//  
//  void update()
//  {
//    this.food = (this.food + this.foodRegen >= this.maxFood) ? this.maxFood : this.food + this.foodRegen;
//    
//    app.rectMode(PConstants.CENTER);
//    switch(this.terrainType)
//    {
//      case GRASS:
//        app.fill(0,255,0);
//        break;
//      case STONE:
//    	app.fill(210);
//        break;
//      case WATER:
//    	app.fill(0, 180, 255);
//        break;
//      case SAND:
//    	app.fill(240, 230, 90);
//        break;
//      case TEMP:
//    	app.fill(255, 255, 0);
//    	break;
//    }
//    app.rect(this.pos.x*app.gridScale, this.pos.y*app.gridScale,app.gridScale,app.gridScale);
//    app.fill(0);
//    //text(this.food, this.pos.x*gridScale-gridScale/2.2, this.pos.y*gridScale+gridScale/2.2);
//  }
//}