package com.prisma.deploy.persistence
import com.prisma.deploy.connector.persistence.InternalMigration
import com.prisma.deploy.specutils.DeploySpecBase
import com.prisma.shared.models.ConnectorCapability
import org.scalatest.{FlatSpec, Matchers, WordSpecLike}

class InternalMigrationPersistenceSpec extends WordSpecLike with Matchers with DeploySpecBase {
  override def doNotRunForCapabilities = Set(ConnectorCapability.MongoJoinRelationLinksCapability)
  val persistence                      = testDependencies.deployConnector.internalMigrationPersistence

  "everything should work" in {
    val migration1 = InternalMigration.RemoveIdColumnFromRelationTables

    persistence.loadAll().await should be(Vector.empty)
    persistence.create(migration1).await
    persistence.loadAll().await should be(Vector(migration1))
  }

}