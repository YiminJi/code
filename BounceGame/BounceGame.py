################################################################################################
#
# Yimin Jiang
# Moxiao Li
# Comp1020
# 3/22/2022
#
# Header comments (Author, Date, Description, Class, etc)
# Assignment A5 - An Avoider Game.
#
# In this assgssigment the creative part we add another enemy this enemy are bigger and with higher damage to player
#
################################################################################################

import math, pygame, random, sys


################################################################################################
# Helper Functions

# pixel_collision()
#   - Test if two sprite masks overlap
#   - Do NOT modify.  This is written for you.
def pixel_collision(mask1, rect1, mask2, rect2):
    offset_x = rect2[0] - rect1[0]
    offset_y = rect2[1] - rect1[1]
    # See if the two masks at the offset are overlapping.
    overlap = mask1.overlap(mask2, (offset_x, offset_y))
    return overlap


################################################################################################
# Sprite Class
#
#   A basic Sprite class that can draw itself, move, and test collisions
#
#   Do NOT modify.  This is written for you.
#


class Sprite:

    def __init__(self, image):
        self.image = image
        self.rectangle = image.get_rect()
        self.mask = pygame.mask.from_surface(image)

    def set_position(self, new_position):
        self.rectangle.center = new_position

    def draw(self, screen):
        screen.blit(self.image, self.rectangle)

    def is_colliding(self, other_sprite):
        return pixel_collision(self.mask, self.rectangle, other_sprite.mask, other_sprite.rectangle)


################################################################################################
# Enemy Class
# In this class we create Enemy and give it function move, bounce and draw. Allow enemy to move and bounce
# around in the screen.
#
#
#


class Enemy:
    def __init__(self, image, screen_width, screen_height):
        self.image = image
        self.mask = pygame.mask.from_surface(image)
        self.rectangle = image.get_rect()
        self.rectangle.center = (random.randrange(screen_width), random.randrange(screen_height))
        self.speed = (random.randint(-10, 10), random.randint(-10, 10))
        self.life_span = random.randint(100, 300)
        #
        # TODO: Add code to
        # 1. Set the rectangle center to a random x and y based
        #    on the screen width and height
        # 2. Set a speed instance variable that holds a tuple (vx, vy)
        #    which specifies how much the rectangle moves each time.
        #    vx means "velocity in x".

    def move(self):
        center_x, center_y = self.rectangle.center
        change_x, change_y = self.speed

        new_x = center_x + change_x
        new_y = center_y + change_y

        #pygame.Rect.move_ip(new_x, new_y)
        self.rectangle.center = (new_x, new_y)

        #
        # TODO: Add code to move the rectangle instance variable in x by
        # the speed vx and in y by speed vy. The vx and vy are the
        # components of the speed instance variable tuple.
        # A useful method of rectangle is pygame's move_ip method.
        # Research how to use it for this task.
        # Remove the print statement when move() is working.

    def bounce(self, screen_width, screen_height):
        change_x = self.speed[0]
        change_y = self.speed[1]
        if self.rectangle.left < 0 or self.rectangle.right > screen_width:
            self.speed = (-change_x, change_y)
        if self.rectangle.top < 0 or self.rectangle.bottom > screen_height:
            self.speed = (change_x, -change_y)


        #
        # TODO: This method makes the enemy bounce off of the top/left/right/bottom
        # of the screen. For example, if you want to check if the object is
        # hitting the left side, you can test
        # if self.rectangle.left < 0:
        # The rectangle.left tests the left side of the rectangle. You will
        # want to use .right .top .bottom for the other sides.
        # The height and width parameters gives the screen boundaries.
        # If a hit of the edge of the screen is detected on the top or bottom
        # you want to negate (multiply by -1) the vy component of the speed instance
        # variable. If a hit is detected on the left or right of the screen, you
        # want to negate the vx component of the speed.
        # Make sure the speed instance variable is updated as needed.
        # Remove the print statement when bounce() is working.

    def draw(self, screen):
        # Same draw as Sprite - Do not modify.
        screen.blit(self.image, self.rectangle)


################################################################################################
# PowerUp Class
#
# In PowerUp Class we give game a recover tool to player.
# In this class we provided a draw method to draw the item.
#
class PowerUp:

    def __init__(self, image, screen_width, screen_height):
        # TODO: Set the PowerUp position randomly like is done for the Enemy class.
        # There is no speed for this object as it does not move.
        self.image = image
        self.mask = pygame.mask.from_surface(image)
        self.rectangle = image.get_rect()
        self.rectangle.center = (random.randrange(screen_width), random.randrange(screen_height))

    def draw(self, screen):
        # Same as Sprite - Do not modify.
        screen.blit(self.image, self.rectangle)


################################################################################################
# DropEnemy Class
#
# A DropEnemy will act like a dropping object under the influence of gravity,
# but still bounce off the sides of the window like the original Enemy class does.

class DropEnemy(Enemy):

    def __init__(self, image, screen_width, screen_height):
        super().__init__(image, screen_width, screen_height)
        self.position = self.rectangle.center

    # @Override
    def move(self):
        # Get the separate vx and vy from the speed instance variable tuple.
        vx, vy = self.speed
        # Add a small value to vy.
        updated_y = vy + 0.5
        # Assign the updated vx and vy to the speed instance variable.
        self.speed = (vx, updated_y)
        # Extract the x and y from the stored position instance variable.
        x, y = self.position
        # Add vx to x and vy to y.
        new_x = x + vx
        new_y = y + vy
        # Store the updated x and y into the position instance variable.
        self.position = (new_x, new_y)
        # Assign the position instance variable to the center of the rectangle instance variable.
        self.rectangle.center = self.position

class PowerUpRotate(PowerUp):
    def __init__(self, original_image, screen_width, screen_height):
        super().__init__(original_image, screen_width, screen_height)
        self.angle = 0
        self.original_image = original_image

    def draw(self, screen):
        rotate_image = pygame.transform.rotate(self.original_image, self.angle)
        self.image = rotate_image
        rectangle = self.rectangle
        self.rectangle = self.image.get_rect()
        self.rectangle.center = rectangle.center
        self.mask = pygame.mask.from_surface(rotate_image)
        self.angle += 10
        super().draw(screen)


################################################################################################

def main():
    # Colors
    TEAL = (0, 128, 128)

    # Setup pygame
    pygame.init()

    clock = pygame.time.Clock()  # Used to control FPS
    FPS = 25  # max frames per second

    # Make the mouse invisible.
    pygame.mouse.set_visible(False)
    # pygame.mouse.set_cursor((8,8),(0,0),(0,0,0,0,0,0,0,0),(0,0,0,0,0,0,0,0))

    # Get a font for printing the lives left on the screen.
    myfont = pygame.font.SysFont('monospace', 24)

    # Define the screen
    s_width, s_height = 600, 400
    size = s_width, s_height
    screen = pygame.display.set_mode(size)

    # Load image assets
    # Choose your own image
    enemy_image = pygame.image.load("enemy.png").convert_alpha()
    enemy_image2 = pygame.image.load("bomb.png").convert_alpha()

    # Here is an example of scaling it to fit a 50x50 pixel size.
    enemy_image = pygame.transform.smoothscale(enemy_image, (50, 50))

    special_enemy = pygame.image.load("dogpolice.png").convert_alpha()

    # TODO: Make some number of initial enemies that will bounce around the screen.
    enemy_sprites = []

    for i in range(8):
        enemy = Enemy(enemy_image, s_width, s_height)
        enemy_sprites.append(enemy)
        enemy2 = DropEnemy(enemy_image2, s_width, s_height)
        enemy_sprites.append(enemy2)

    special_enemies = []
    for x in range(2):
        enemy2 = Enemy(special_enemy, s_width, s_height)
        special_enemies.append(enemy2)

    # This is the character you control. TODO: Choose your image.
    player_image = pygame.image.load("player.png").convert_alpha()
    player_sprite = Sprite(player_image)
    health = 3

    # This is the powerup image. TODO: Choose your image.
    powerup_image = pygame.image.load("powerup.png").convert_alpha()
    powerup_image2 = pygame.image.load("gold.png").convert_alpha()
    # Start with an empty list of powerups and add them (in the main game loop) as the game runs.
    powerups = []

    # Main Game Loop
    is_playing = True
    pause = True  # Whether to pause after game ends...
    frame = 1

    while is_playing:
        # TODO: Modify the loop to stop when health is <= to 0.

        # Check for events
        for event in pygame.event.get():
            # Stop loop if user clicks on window close button
            if event.type == pygame.QUIT:
                is_playing = False
                pause = False  # User killed game, don't pause...
                continue
            elif health <= 0:
                is_playing = False
                pause = False
                continue

        # Make the player follow the mouse
        pos = pygame.mouse.get_pos()
        player_sprite.set_position(pos)

        # TODO: Make a new Enemy instance once each second and add it to enemy_sprites.
        #   - Use the frame number mod FPS to know when a second has passed.
        #   - You might choose to change how / when new enemies are created
        #     based on your Creative Element game addition.
        if frame == 30:
            enemy = Enemy(enemy_image, s_width, s_height)
            enemy_sprites.append(enemy)
            frame = 1

        if frame == 30:
            enemy2 = Enemy(special_enemy, s_width, s_height)
            special_enemies.append(enemy2)
            frame = 1

        # TODO:  Loop over the enemy sprites. If the player sprite is
        # colliding with an enemy, deduct from the health variable.
        # A player is likely to overlap an enemy for a few iterations
        # of the game loop - experiment to find a small value to deduct that
        # makes the game challenging but not frustrating.
        for enemy in enemy_sprites:
            if player_sprite.is_colliding(enemy):
                health -= 1

        for people in special_enemies:
            if player_sprite.is_colliding(people):
                health -= 5

        # TODO: Loop over the powerups. If the player sprite is colliding, add
        # 1 to health.
        power_sprite = PowerUp(powerup_image, s_width, s_height)
        powerups.append(power_sprite)
        for powerup in powerups:
            if player_sprite.is_colliding(powerup):
                health += 1

        # TODO: Make a list comprehension that removes powerups that are colliding with
        # the player sprite.
        powerups = [powerup for powerup in powerups if not player_sprite.is_colliding(powerup)]

        # TODO: Loop over the enemy_sprites. Each enemy should call move and bounce.
        for item in enemy_sprites:
            if enemy_sprites.index(item) % 2 == 0:
                item.move()
                item.bounce(s_width, s_height)
            else:
                item.move()

        for people in special_enemies:
            people.move()
            people.bounce(s_width, s_height)

        enemy_sprites = [e for e in enemy_sprites if e.life_span > frame]
        # for n_enemy in new_enemies:
        #     if n_enemy in enemy_sprites:
        #         enemy_sprites.remove(n_enemy)

        # pygame.display.update()
        # pygame.time.wait(int(1000 / FPS))


        # TODO: Choose a random number. Use the random number to decide to add a new
        # powerup to the powerups list. Experiment to make them appear not too
        # often, so the game is challenging.
        random_number = random.randint(0, 10)
        new_power_up = PowerUp(powerup_image, s_width, s_height)
        power_sprite2 = PowerUpRotate(powerup_image2, s_width, s_height)
        if random_number == 1:
            powerups.append(new_power_up)
            powerups.append(power_sprite2)

        # Erase the screen with a background color
        screen.fill(TEAL)  # fill the window with a color

        # Draw the characters
        for enemy_sprite in enemy_sprites:
            enemy_sprite.draw(screen)

        # Draw the powerups
        for powerup_sprite in powerups:
            powerup_sprite.draw(screen)

        for test in special_enemies:
            test.draw(screen)

        player_sprite.draw(screen)

        # Display the player's health on the screen.
        text = "Health: " + str('%.1f' % health)
        label = myfont.render(text, True, (255, 255, 0))
        screen.blit(label, (20, 20))

        # Bring all the changes to the screen into view
        pygame.display.update()

        # Pause for a few milliseconds
        # pygame.time.wait( 20 )
        clock.tick(FPS)
        frame += 1

    # Once the game loop is done, pause, close the window and quit.
    # Pause for a few seconds
    if pause:
        pygame.time.wait(2000)

    pygame.quit()
    sys.exit()


################################################################################################

if __name__ == "__main__":
    main()
