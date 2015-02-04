import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;

import javax.swing.*;

/**
   A program that allows users to edit a scene composed
   of items.
*/
public class CheckersGame
{
   public static void main(String[] args)
   {
      final JFrame frame = new JFrame();

      final CheckerBoard scene = new CheckerBoard();
      JButton restart = new JButton("Restart");
      
      restart.addActionListener (new ActionListener()
		{
			public void actionPerformed (ActionEvent ae)
			{	
				scene.reset();
			}
		});
      
      frame.add(restart, BorderLayout.NORTH);
      frame.add (scene, BorderLayout.CENTER);
      
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
      frame.setSize (600, 600);
      frame.setVisible (true);
   }
}


