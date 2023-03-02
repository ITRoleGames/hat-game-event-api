import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "1.7.21"
	id("io.gitlab.arturbosch.detekt") version "1.22.0"
}

group = "rubber.dutch.hat"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.22.0")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.getByName<Jar>("jar") {
	enabled = false
}

tasks.withType<Test> {
	useJUnitPlatform()
}

detekt {
	source = objects.fileCollection().from(
			io.gitlab.arturbosch.detekt.extensions.DetektExtension.DEFAULT_SRC_DIR_JAVA,
			io.gitlab.arturbosch.detekt.extensions.DetektExtension.DEFAULT_TEST_SRC_DIR_JAVA,
			io.gitlab.arturbosch.detekt.extensions.DetektExtension.DEFAULT_SRC_DIR_KOTLIN,
			io.gitlab.arturbosch.detekt.extensions.DetektExtension.DEFAULT_TEST_SRC_DIR_KOTLIN,
	)
	buildUponDefaultConfig = true
	baseline = file("$rootDir/config/detekt/baseline.xml")
	config = files("$rootDir/config/detekt/detekt.yml","$rootDir/config/detekt/detekt-custom.yml")
}