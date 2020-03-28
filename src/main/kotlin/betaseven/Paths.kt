package betaseven

import screeps.api.*
import screeps.api.options
import screeps.api.structures.Structure

object Paths {
    fun pathForWorker(startPos: RoomPosition, goals: Array<RoomPosition>): Array<RoomPosition> {
        val path = PathFinder.search(startPos, goals, options {
            plainCost = 2
            swampCost = 10

            roomCallback = { roomName: String ->
                val room = Game.rooms[roomName]!!
                val costs = PathFinder.CostMatrix()

                room.find(FIND_STRUCTURES).forEach { structure: Structure ->
                    if (structure.structureType == STRUCTURE_ROAD) {
                        costs.set(structure.pos.x, structure.pos.y, 1)
                    } else if (structure.structureType != STRUCTURE_CONTAINER &&
                        (structure.structureType != STRUCTURE_RAMPART ||
                                !structure.my)) {
                        costs.set(structure.pos.x, structure.pos.y, 0xff)
                    }
                }

                room.find(FIND_CREEPS).forEach { creep: Creep ->
                    costs.set(creep.pos.x, creep.pos.y, 0xff)
                }

                costs
            }
        })

        return if (path.incomplete) emptyArray() else path.path
    }
}
