# ***This is a Swing project for Ashkelon***

## Java Swing Top-Down Shooter:

A 2D top-down shooter made with **Java Swing**, where the player shoots enemies coming in waves.

## 🔧 Core Features

- **Player**: Moves with keyboard, shoots bullets.
- **Enemies**: Spawn in waves, move toward player.
- **Waves**: Each wave increases in difficulty (more/faster enemies).
- **Bullets**: Fired by player, destroy enemies on hit.
- **Game Loop**: Uses `Thread` to update and repaint.

## rough file structure:

    main/ 
        java/
            entities/
                Entity.java
                Enemy.java
                Player.java
            main/
                Game.java
                GamePanel.java
                GameLoop.java
            menu/
                MainFrame.java
                MainMenu.java
                InstructionsScreen.java
            utils/
                EnemySpawner.java
                InputHandler.java
                
        resources/
            assets/
                Enemy.png
                newEnemy.png


## logs:
    v0.0.1 - 
        1. setting up

    v0.1.0-a - 
        1. added rough Enemy + EnemySpawner.
        2. made GamePalen and GameLoop.
        3. done a little of polish...

