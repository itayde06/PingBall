����   A
      danogl/GameManager <init> *(Ljava/lang/String;Ldanogl/util/Vector2;)V
   	 
 update (F)V
      BouncingBallGameManger checkForGameEnd ()V	     ball Lgameobjects/Ball;
      gameobjects/Ball 	getCenter ()Ldanogl/util/Vector2;
       danogl/util/Vector2 y ()F "  	  $ % & windowDimensions Ldanogl/util/Vector2; ( 	You Lose! * You win!
 , - . / 0 java/lang/String isEmpty ()Z   2 3 4 makeConcatWithConstants &(Ljava/lang/String;)Ljava/lang/String;	  6 7 8 windowController Ldanogl/gui/WindowController; : ; < = > danogl/gui/WindowController openYesNoDialog (Ljava/lang/String;)Z : @ A  	resetGame : C D  closeWindow
  F G H initializeGame n(Ldanogl/gui/ImageReader;Ldanogl/gui/SoundReader;Ldanogl/gui/UserInputListener;Ldanogl/gui/WindowController;)V : J K L setTargetFramerate (I)V N danogl/GameObject	  P Q & ZERO : S T  getWindowDimensions V assets/DARK_BG2_small.jpeg
 X Y Z [ \ danogl/gui/ImageReader 	readImage ;(Ljava/lang/String;Z)Ldanogl/gui/rendering/ImageRenderable;
 M ^  _ N(Ldanogl/util/Vector2;Ldanogl/util/Vector2;Ldanogl/gui/rendering/Renderable;)V	 a b c d e !danogl/components/CoordinateSpace CAMERA_COORDINATES #Ldanogl/components/CoordinateSpace;
 M g h i setCoordinateSpace &(Ldanogl/components/CoordinateSpace;)V
  k l m gameObjects *()Ldanogl/collisions/GameObjectCollection; o danogl/collisions/Layer
 q r s t u &danogl/collisions/GameObjectCollection addGameObject (Ldanogl/GameObject;I)V
 q w t x (Ldanogl/GameObject;)V z assets/mockBall.png | assets/blop_cut_silenced.wav
 ~  � � � danogl/gui/SoundReader 	readSound &(Ljava/lang/String;)Ldanogl/gui/Sound;B  
  �  � (FF)V
  �  � `(Ldanogl/util/Vector2;Ldanogl/util/Vector2;Ldanogl/gui/rendering/Renderable;Ldanogl/gui/Sound;)VC�   � java/util/Random
 � �  
 � � � 0 nextBoolean��  
  � � � setVelocity (Ldanogl/util/Vector2;)V?   
  � � � mult (F)Ldanogl/util/Vector2;
  � � � 	setCenter � assets/paddle.png � gameobjects/PaddleB�  A�  
 � �  � l(Ldanogl/util/Vector2;Ldanogl/util/Vector2;Ldanogl/gui/rendering/Renderable;Ldanogl/gui/UserInputListener;)V
  � �   x
 M � � gameobjects/AIPaddle
 � �  � a(Ldanogl/util/Vector2;Ldanogl/util/Vector2;Ldanogl/gui/rendering/Renderable;Ldanogl/GameObject;)VA�   � assets/border.pngA    � BrickerD/  C�  
  
  � �  run BORDER_WIDTH I ConstantValue   
 PADDLE_HEIGHT    PADDLE_WIDTH   d BALL_RADIUS   # 
BALL_SPEED F BRICK_HEIGHT BRICK_WIDTH   � Code LineNumberTable LocalVariableTable this LBouncingBallGameManger; windowTitle Ljava/lang/String; 	deltaTime 
ballHeight prompt StackMapTable imageReader Ldanogl/gui/ImageReader; soundReader Ldanogl/gui/SoundReader; inputListener Ldanogl/gui/UserInputListener; 
background Ldanogl/GameObject; 	ballImage !Ldanogl/gui/rendering/Renderable; collisionSound Ldanogl/gui/Sound; ballVelX ballVelY rand Ljava/util/Random; paddleImage 
userPaddle aiPaddle borderImage � danogl/gui/UserInputListener � danogl/gui/rendering/Renderable � danogl/gui/Sound main ([Ljava/lang/String;)V args [Ljava/lang/String; 
SourceFile BouncingBallGameManger.java BootstrapMethods �  Play again? �
 � � � 3 � $java/lang/invoke/StringConcatFactory �(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite; InnerClasses %java/lang/invoke/MethodHandles$Lookup java/lang/invoke/MethodHandles Lookup !     
  � �  �    �  � �  �    �  � �  �    �  � �  �    �  � �  �    �  � �  �    �  � �  �    �       % &    7 8        �   I     *+,� �    �   
        �         � �      � �     % &   	 
  �   F     
*#� *� �    �       %  & 	 ) �       
 � �     
 � �      �   �     b*� � � D!M#*� #� �� 'M#�� )M,� +� 7,� 1  M*� 5,� 9 � *� 5� ? *� 5,� 9 � *� 5� B �    �   6    ,  -  .  /  1 # 2 & 5 - 6 4 7 B 8 K ; X < a @ �        b � �    W � �   T � �  �    �  ,$  G H  �  H    �*� 5*+,-� EP� I � MY� O� R +U� W� ]:� `� f*� j�8� p*� j� v+y� W:,{� }:*� Y� O� Y��� �� �� �8�8	� �Y� �:

� �� 
�j8
� �� 
	�j8	*� � Y	� �� �*� R � #*� *� #�� �� �*� j*� � v+�� W:� �Y� O� Y��� �-� �:� Y*� #� �n*� #� �d�� �� �*� j� v� �Y� O� Y��� �*� � �:� Y*� #� �n�� �� �*� j� v+�� W:*� j� MY� O� Y�*� #� � �� ]� v*� j� MY� Y*� #� ��f� �� Y�*� #� � �� ]� v�    �   � '   E  F  G  J ! L * M 2 N : O F P O T X U ` V } W � X � Y � Z � [ � \ � ] � ^ � ` � a � b � e � g l( m1 pN qe rn uw w� z� w� }� � �� }� � �   �   � � �    � � �   � � �   � � �   � 7 8  2� � �  Xu � �  `m � �  �L � �  �H � � 	 �? � � 
 � � � �  � � � N  � � w V � �  �   ' � �   X ~ � : M � � �   	 � �  �   F     � Y�� Y��� �� �� ��    �   
    �  � �        � �    �    � �     �  �    
  