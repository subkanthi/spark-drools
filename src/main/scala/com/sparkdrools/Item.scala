package com.sparkdrools

import java.io.Serializable
import java.util.Objects


/**
 * Example from Drools Book.
 */
@SerialVersionUID(1L)
object Item {

  object Category extends Enumeration {
    type Category = Value
    val NA, LOW_RANGE, MID_RANGE, HIGH_RANGE, SPECIAL_MIDHIGH_RANGE //used in chapter 4 = Value
  }

}

@SerialVersionUID(1L)
class Item() extends Serializable {
  private var id = 0L
  private var name = ""
  private var cost = .0
  private var salePrice = .0
  private var category = ""

  def this(id: Long, name: String, cost: Double, salePrice: Double) {
    this()
    this.id = id
    this.name = name
    this.cost = cost
    this.salePrice = salePrice
    this.category = Item.Category.NA
  }

  def this(name: String, cost: Double, salePrice: Double) {
    this(null, name, cost, salePrice)
  }

  def getName: String = name

  def setName(name: String): Unit = {
    this.name = name
  }

  def getCost: Double = cost

  def setCost(cost: Double): Unit = {
    this.cost = cost
  }

  def getSalePrice: Double = salePrice

  def setSalePrice(salePrice: Double): Unit = {
    this.salePrice = salePrice
  }

  def getId: Long = id

  def setId(id: Long): Unit = {
    this.id = id
  }
//
//  def getCategory: Category = category
//
//  def setCategory(category: Category): Unit = {
//    this.category = category
//  }

  override def hashCode: Int = {
    var hash = 3
    hash = 59 * hash + Objects.hashCode(this.id)
    hash = 59 * hash + Objects.hashCode(this.name)
    hash = 59 * hash + Objects.hashCode(this.cost)
    hash = 59 * hash + Objects.hashCode(this.salePrice)
    hash = 59 * hash + Objects.hashCode(this.category)
    hash
  }

  override def equals(obj: Any): Boolean = {
    if (obj == null) return false
    if (getClass ne obj.getClass) return false
    val other = obj.asInstanceOf[Item]
    if (!Objects.equals(this.id, other.id)) return false
    if (!Objects.equals(this.name, other.name)) return false
    if (!Objects.equals(this.cost, other.cost)) return false
    if (!Objects.equals(this.salePrice, other.salePrice)) return false
    if (this.category ne other.category) return false
    true
  }

  override def toString: String = "Item.Item{" + "id=" + id + ", name=" + name + ", cost=" + cost + ", salePrice=" + salePrice + ", category=" + category + '}'
}

