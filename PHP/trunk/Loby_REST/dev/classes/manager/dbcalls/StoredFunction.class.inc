<?php
class StoredFunction
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
		$this->get_validator()
				->validate();
		$result = $this->get_manager()
				->execute_procedure($this->name, $data);
	}

}
