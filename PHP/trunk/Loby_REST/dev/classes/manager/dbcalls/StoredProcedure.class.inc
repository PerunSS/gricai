<?php
class StoredProcedure
		extends DBCall {

	/**
	 * @var String
	 */

	private $name;

	public function __construct($name) {
		$this->name = $name;
		$this->setManager(new Manager());
	}

	public function execute($data) {
		$this->getValidator()
				->validate();
		$result = $this->getManager()
				->executeProcedure($this->name, $data);
		return $this->getFormater()
				->format($result);
	}

}
