# ***This is a Swing project for Ashkelon***

we are just started...


## rough file structure:

        src/
    │
    ├── main/
    │   ├── Game.java                 # מחלקת ה-main, מפעילה את המשחק
    │   ├── GameWindow.java           # חלון המשחק הראשי
    │   ├── GamePanel.java            # JPanel בו מתרחש המשחק
    │   ├── GameLoop.java             # Thread ראשי שמפעיל את המשחק בלולאה
    │
    ├── entities/
    │   ├── Entity.java               # מחלקת בסיס עם תכונות נפוצות
    │   ├── Player.java               # שחקן
    │   ├── Enemy.java                # אויב
    │   ├── Bullet.java               # קליעים
    │   └── Obstacle.java            # מכשולים
    │
    ├── utils/
    │   ├── InputHandler.java         # אחראי לקלט מהמקלדת
    │   ├── CollisionDetector.java    # בדיקת התנגשויות
    │   ├── SoundManager.java         # ניהול סאונד
    │   └── ScoreManager.java         # ניקוד ושמירה
    │
    ├── assets/
    │   ├── images/                   # תמונות של דמויות ורקעים
    │   └── sounds/                   # מוזיקה ואפקטים
    │
    └── menu/
    ├── MainMenu.java             # מסך פתיחה עם כפתור "התחל"
    └── InstructionsScreen.java   # מסך הוראות
    
    docs/
    ├── presentation.pptx             # מצגת להצגה
    └── README.md                     # תיאור הפרויקט


## logs:
    v0.0.1 - setting up

