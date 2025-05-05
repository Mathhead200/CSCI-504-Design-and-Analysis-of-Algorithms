# Set up:
# We have a robot (R) on a grid.
# The robot can only move right and down (no loops).
# The robot can not move past the edge of the grid.
# The robot can not move onto obsticles (x).
# 
# R------x
# --x-xx--
# x------*
# 
# Goal:
# We want to compute the number of different possible paths the robot can take
# from its starting position to the goal (*).
#
# Algorithm:
# Consider the robot at position (x0, y0) with some set of possible moves,
# {Move[i]}. Define Move[i](x0,y0) = (x1,y1) where (x1,y1) is the position of
# the robot after performing Move[i] from position (x0,y0).
#
# For example, the robot is at position (0,0) in the above example grid. Their
# available moves are {East, South}. East(0,0) = (1,0) and South(0,0) = (0,1).
# That is, the East move increments the robot's x position by one, and the
# South move increments the robot's y position by one. 
# 
# Define the number of possible path the robot can take from a posistion (x,y)
# as Paths(x,y).
# Then Path(x0,y0) = Sum of Path(Move[i]) for all possible moves in {Move[i]}
# That is, if we know the robot has 4 posible paths if they move East, and only
# 1 possible path if they move South, then the robot must have 5 total paths
# from this position.
class Grid:
	East = lambda pos: (pos[0] + 1, pos[1])
	South = lambda pos: (pos[0], pos[1] + 1)

	def __init__(self, width, height):
		self.width = width
		self.height = height
		self.robot = (0,0)
		self.goal = (width - 1, height - 1)
		self.obsticles = []  # list<tuple(2)>
	
	def moves(self, pos = None):
		if pos is None:
			pos = self.robot
		
		moves = set()
		dest = Grid.East(pos)
		if dest[0] < self.width and dest not in self.obsticles:
			moves.add(Grid.East)
		dest = Grid.South(pos)
		if dest[1] < self.height and dest not in self.obsticles:
			moves.add(Grid.South)
		return moves

	# Given a position (and cache), return a list of possible path.
	# Each path is itself a list of positions (tuple(x, y)).
	# An example path might be
	# 	[(0,0), (1,0), (2,0), (3,0), (3,1), (3,2), (4,2), (5,2), (6,2), (7,2)]
	def _paths(self, pos, cache):
		# 1. Check if pos is in cache
		if pos in cache:
			return cache[pos]
		
		# 2. Recursively get sub-solutions, and construct solution
		paths = []
		for move in self.moves(pos):
			for route in self._paths(move(pos), cache):  # recursion: returns list of lists
				# Create a unique path for each unique route, but each staring
				# from the same position (pos).
				path = [pos]
				path.extend(route)
				paths.append(path)
		
		# 3. Store (cache) and return results
		cache[pos] = paths
		return paths


	def paths(self):
		# Start the cache with only the trival solution:
		# 	if the robot is at the goal, there is only one path which contains
		# 	only the goal (position) itself.
		return self._paths(pos = self.robot, cache = { self.goal: [[self.goal]] })

	def _symbol(self, pos, path = []):
		if pos == self.robot:
			return "R"
		if pos == self.goal:
			return "*"
		if pos in self.obsticles:
			return "x"
		if pos in path:
			return "+"
		return "-"

	def __repr__(self, path = []):
		return "\n".join(["".join([self._symbol((x,y), path) for x in range(self.width)]) for y in range(self.height)])
	
	def highlight(self, path):
		print(self.__repr__(path))

# App entry point.
if __name__ == "__main__":
	grid = Grid(8, 3)
	grid.obsticles = [(7,0), (2,1), (4,1), (5,1), (0,2)]
	print(grid)

	paths = grid.paths()
	n = len(paths)
	print("Number of paths:", n)
	print("These are the paths:")
	for i in range(n):
		print(i + 1, ". ", paths[i], sep = "")
		grid.highlight(paths[i])
	