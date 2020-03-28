package betaseven

import screeps.api.RESOURCE_ENERGY
import screeps.api.STRUCTURE_EXTENSION
import screeps.api.STRUCTURE_SPAWN
import screeps.api.structures.Structure
import screeps.api.structures.StructureExtension
import screeps.api.structures.StructureSpawn

object Structures {
    fun needsEnergy(structure: Structure): Boolean {
        return when (structure.structureType) {
            STRUCTURE_SPAWN -> {
                val castStruct = structure as StructureSpawn
                castStruct.store.getFreeCapacity(RESOURCE_ENERGY)!! > 0
            }
            STRUCTURE_EXTENSION -> {
                val castStruct = structure as StructureExtension
                castStruct.store.getFreeCapacity(RESOURCE_ENERGY)!! > 0
            }
            else -> {
                false
            }
        }
    }
}
