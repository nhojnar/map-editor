/*import processing.core.*;

class Button
{
	final private int w = 100;
	final private int h = 30;
	PVector pos;
	Action myAction;
	String label;
	int rectColor, rectHighlight, currentColor;
	Main app;

	Button(PVector pos, String label, Action myAction, Main app)
	{
		this.pos = pos;
		this.label = label;
		this.myAction = myAction;
		this.app = app;
		rectColor = (65);
		rectHighlight = (155);
	}
	Button(int x, int y, String label, Action myAction, Main app)
	{
		this(new PVector(x,y), label, myAction, app);
	}

	boolean overRect()
	{
		return (app.mouseX >= pos.x - w/2 && app.mouseX <= pos.x + w/2) && (app.mouseY >= pos.y - h/2 && app.mouseY <= pos.y + h/2);
	}

	void update()
	{
		app.translate(-app.map.gridScale/2,-app.map.gridScale/2);
		app.rectMode(PConstants.CENTER);
		app.textAlign(PConstants.LEFT);
		currentColor = overRect() ? rectHighlight : rectColor;
		app.fill(currentColor);
		app.rect(pos.x, pos.y, w, h);
		app.fill((currentColor > 150) ? 0 : 255);
		app.text(label, pos.x, pos.y);
	}
}
interface Action {void execute();}*/