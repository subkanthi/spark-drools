package com.sparkdrools


  import org.apache.hadoop.fs.shell.CommandFactory
  import org.apache.spark.{SparkConf, SparkContext}
  import org.apache.spark.api.java.function.Function
  import org.apache.spark.broadcast.Broadcast
  import org.kie.api.KieBase
  import org.kie.api.KieServices
  import org.kie.api.runtime.KieContainer
  import org.kie.api.runtime.StatelessKieSession
  import org.kie.internal.command.CommandFactory


  object App {
    def main(args: Array[String]): Unit = {
      val inputData = util.Arrays.asList(
        new Nothing(1, "John", "Doe", 10000, 568),
        new Nothing(2, "John", "Greg", 12000, 654),
        new Nothing(3, "Mary", "Sue", 100, 568),
        new Nothing(4, "Greg", "Darcy", 1000000, 788),
        new Nothing(5, "Jane", "Stuart", 10, 788))


      val conf = new SparkConf().setAppName("Simple Application")
      val sc = new SparkContext()(conf)
      val rules = loadRules
      val broadcastRules = sc.broadcast(rules)
      val applicants = sc.parallelize(inputData)
      val numApproved = applicants.map((a: Nothing) => applyRules(broadcastRules.value, a)).filter((a: Any) => a.isApproved).count
      System.out.println("Number of applicants approved: " + numApproved)
    }

    def loadRules: KieBase = {
      val kieServices = KieServices.Factory.get
      val kieContainer = kieServices.getKieClasspathContainer
      kieContainer.getKieBase
    }

    def applyRules(base: KieBase, a: Nothing): Item = {
      val session = base.newKieSession()
      session.insert()
      //session.execute(CommandFactory)
      session.execute(CommandFactory.newInsert(a))

      this.kieSession = KieContainer

      this.kieSession.insert(item);
      return this.kieSession.fireAllRules();


    }
  }

  class App {}

